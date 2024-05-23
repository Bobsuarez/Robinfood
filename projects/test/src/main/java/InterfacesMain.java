import org.example.ObjectMapperSingletonUtil;

import java.util.ArrayList;
import java.util.List;

public class InterfacesMain {

    public static void main(String[] args) {

        List<datados> datados = new ArrayList<>();

        for (datados datado : datados) {
            datado.nuevo();
        }

        System.out.println(datados.size());

    }
}

class nuevo implements datados {

    @Override
    public void nuevo() {
        System.out.println("nuevo");
    }
}

class nuevodos implements datados {

    @Override
    public void nuevo() {
        System.out.println("nuevodos");
    }
}

