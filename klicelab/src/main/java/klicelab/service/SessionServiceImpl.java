package klicelab.service;

import klicelab.model.Session;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by klice on 2017/5/23.
 * 会话服务
 */
@Service
public class SessionServiceImpl implements SessionService {
    static Map<String, Session> sessionMap = new HashMap<String, Session>();

    @Override
    public Session get(String sessionId) {
        if (sessionMap.containsKey(sessionId))
            return sessionMap.get(sessionId);
        return null;
    }

    @Override
    public void save(Session session) {
        if (session == null || session.getId() == null)
            return;
        sessionMap.put(session.getId(), session);
    }
}
