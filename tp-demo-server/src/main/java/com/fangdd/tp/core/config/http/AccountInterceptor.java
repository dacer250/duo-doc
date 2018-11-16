package com.fangdd.tp.core.config.http;

import com.fangdd.tp.core.annotation.Account;
import com.fangdd.tp.core.exceptions.Http401Exception;
import com.fangdd.tp.core.exceptions.Http403Exception;
import com.fangdd.tp.core.exceptions.TpServerException;
import com.fangdd.tp.dto.UserContent;
import com.fangdd.tp.entity.Team;
import com.fangdd.tp.entity.User;
import com.fangdd.tp.enums.RoleEnum;
import com.fangdd.tp.helper.UserContextHelper;
import com.fangdd.tp.service.TeamService;
import com.fangdd.tp.service.UserService;
import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Account 注解的验证
 */
public class AccountInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AccountInterceptor.class);
    private static final String AUTH_TOKEN = "auth-token";

    private static final Cache<String, Team> CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES) //5分钟后过期
            .maximumSize(10000) //最多1万个key
            .build();
    private static final String HOST = "Host";

    private UserService userService;

    private TeamService teamService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserContent userContent = new UserContent();

        String host = request.getHeader(HOST);
        Team team = getTeam(host);
        userContent.setTeam(team);

        User user = null;
        String authToken = request.getHeader(AUTH_TOKEN);
        if (!Strings.isNullOrEmpty(authToken)) {
            user = userService.getByToken(authToken);
            if (user != null) {
                user.getAuths().forEach(authInfo -> {
                    authInfo.setAccessToken(null);
                    authInfo.setRefreshToken(null);
                    authInfo.setGid(null);
                });
                user.setCurrentTeam(team);
                userContent.setUser(user);
            }
        }
        UserContextHelper.setUserContext(userContent);


        Method method = ((HandlerMethod) handler).getMethod();
        Account account = method.getAnnotation(Account.class);
        if (account == null) {
            //未限制
            return true;
        }
        if (!checkAccount(user, account)) {
            throw new Http403Exception();
        }
        return true;
    }

    private Team getTeam(String domain) {
        Team team;
        try {
            team = CACHE.get(domain, () -> teamService.getByHost(domain));
        } catch (ExecutionException e) {
            logger.error("获取team信息失败：domain={}", domain, e);
            throw new TpServerException(500, "获取team信息失败");
        } catch (CacheLoader.InvalidCacheLoadException e) {
            //返回了空对象
            throw new TpServerException(404, "无效域名！", e);
        }
        
        return team;
    }

    private boolean checkAccount(User user, Account account) {
        if (user == null) {
            throw new Http401Exception();
        }

        if (RoleEnum.ADMIN.getRole() == user.getRole()) {
            return true;
        }

        return account.value().getRole() <= user.getRole();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        UserContextHelper.setUserContext(null);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}
