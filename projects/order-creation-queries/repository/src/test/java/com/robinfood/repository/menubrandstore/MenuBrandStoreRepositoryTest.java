package com.robinfood.repository.menubrandstore;

import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.menu.MenuBrandEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.MenuBcAPI;
import com.robinfood.repository.mocks.MenuBrandEntityMock;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuBrandStoreRepositoryTest {

    @Mock
    private MenuBcAPI menuBcAPI;

    @Mock
    private Call<APIResponseEntity<List<MenuBrandEntity>>> mockMenuBrandEntityCall;

    @InjectMocks
    private MenuBrandStoreRepository menuBrandStoreRepository;

    @Test
    void test_Menu_Brand_Store_Returns_Successfully() throws Exception {
        when(menuBcAPI.getMenuBrandStore(
                anyString(),
                anyLong()
        )).thenReturn(mockMenuBrandEntityCall);

        when(mockMenuBrandEntityCall.execute()).thenReturn(Response.success(new APIResponseEntity<>(
                200,
                List.of(MenuBrandEntityMock.getDataDefault()),
                "CO",
                "",
                "Order daily returned"
        )));

        menuBrandStoreRepository.getMenuBrandStore(1L, "token");

        verify(menuBcAPI).getMenuBrandStore(anyString(), anyLong());
    }

    @Test
    void test_Menu_Brand_Store_Returns_With_Failure() throws Exception {
        when(menuBcAPI.getMenuBrandStore(anyString(), anyLong())).thenReturn(mockMenuBrandEntityCall);

        when(mockMenuBrandEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                        MediaType.parse("application/json"),
                        ObjectExtensions.toJson(new APIResponseEntity<>(
                                500,
                                List.of(MenuBrandEntityMock.getDataDefault()),
                                "CO",
                                "",
                                "Order daily not be returned"
                        ))
                ))
        );

        menuBrandStoreRepository.getMenuBrandStore(1L, "token");

        verify(menuBcAPI).getMenuBrandStore(anyString(), anyLong());
    }
}