/**
 * Created by klice on 2017/5/18.
 * test for UserService
 */
package klicelab.service;

import klicelab.model.User;
import klicelab.persistence.UserMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.mockito.Mockito.*;

import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLDataException;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Mock
    UserMapper mapper;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userNameExist(){
        String userName = "name1";
        UserServiceImpl userService = new UserServiceImpl(mapper);

        when(mapper.getByName(userName)).thenReturn(new User());
        Assert.assertTrue(userService.userNameExist(userName));
        verify(mapper).getByName(userName);

        when(mapper.getByName(userName)).thenReturn(null);
        Assert.assertFalse(userService.userNameExist(userName));

        when(mapper.getByName(userName)).thenThrow(new RuntimeException("user not found",new SQLDataException()));
        thrown.expect(RuntimeException.class);
        userService.userNameExist(userName);
    }
}
