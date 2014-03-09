package dao;

import commons.domain.dao.GenericJpaDao;
import domain.UserEntity;
import org.springframework.util.Assert;

import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 * Created by Павел on 09.03.14.
 */
public class UserJpaDao extends GenericJpaDao<UserEntity, Long> implements UserDao {

    public UserJpaDao() {
        super(UserEntity.class);
    }

    @Override
    public boolean checkAvaliable(String userName) {
        Assert.notNull(userName);
        Query query = getEntityManager().createQuery("select count(*) from " + getPersistentClass().getSimpleName() +
                " u where u.userName = :userName").setParameter("userName", userName);

        Long count = (Long) query.getSingleResult();

        return count < 1;
    }

    @Override
    public UserEntity loadUserByUserName(String userName) {
        Assert.notNull(userName);

        UserEntity user = null;
        Query query = getEntityManager().createQuery("select u from " + getPersistentClass().getSimpleName() +
                " u where u.userName = :userName").setParameter("userName", userName);
        try {
            user = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        return user;
    }

}
