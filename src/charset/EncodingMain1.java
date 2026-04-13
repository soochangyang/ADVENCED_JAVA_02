package charset;

import java.nio.charset.Charset;
import static java.nio.charset.StandardCharsets.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EncodingMain1 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");
    public static void main(String[] args) {
        System.out.println("== ASCII 영문 처리 ==");
        encoding("A", US_ASCII);
        encoding("A", ISO_8859_1);
        encoding("A", EUC_KR);
        encoding("A", UTF_8);
        encoding("A", UTF_16BE); //2byte  호환이 안됨

        System.out.println("== 한글 지원 ==");
        encoding("가", EUC_KR);  // 2byte
        encoding("가", MS_949);  // 2byte
        encoding("가", UTF_8);  // 3byte
        encoding("가", UTF_16BE);  // 2byte
        
        String str = "hello";
        byte[] bytes = str.getBytes(); // charset 을 지정하지 않으면 기본 문자열 사용
        System.out.println("bytes = " + bytes);

    }


    private static void encoding(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte\n ", text, charset, Arrays.toString(bytes), bytes.length);


    }
}
