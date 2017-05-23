package klicelab.service;

import klicelab.model.Session;

/**
 * Created by klice on 2017/5/23.
 * 会话服务
 */
public interface SessionService {
    /**
     * 根据会话id查询会话
     * @param sessionId 会话id
     * @return 会话
     */
    Session get(String sessionId);

    /**
     * 存储会话
     * @param session 会话
     */
    void save(Session session);
}
