import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        String link = "";
        String keyword = "";
        int level = 2;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter URL: ");
            link = br.readLine();

            System.out.print("Keyword: ");
            keyword = br.readLine();
            System.out.print("Level: ");
            level = Integer.parseInt(br.readLine());
        }catch (Exception e){
            e.getStackTrace();
        }
        Spider spider = new Spider();
        spider.processLinks("https://" + link, level, keyword);
        spider.printLink();
    }
}
