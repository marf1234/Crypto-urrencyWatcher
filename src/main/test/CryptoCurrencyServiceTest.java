import com.vegera.CryptocurrencyWatcherApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CryptocurrencyWatcherApplication.class)
class CryptoCurrencyControllerTest {

}