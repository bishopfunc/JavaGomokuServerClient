package GPTGomoku;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.util.ArrayList;
import org.json.JSONObject;
import netscape.javascript.JSObject;

public class GPTRGomoku {
    public static void main(String[] args) throws IOException {
        String APIKey = args[0];
        System.out.println(APIKey);
        GPTRoom room = new GPTRoom(APIKey);
        room.loadSetting();
        Board board = new Board();
        int i, j;
        while (true) {
            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("あなたの入力: ");
                i = sc.nextInt();
                j = sc.nextInt();
                if (!board.checkExist(i - 1, j - 1)) {
                    board.addBoard(i - 1, j - 1, 1);
                    System.out.println(board.getBoardText());
                    break;
                } else {
                    System.out.println("すでに石が存在します");
                }
            }

            String output = room.talk("(" + i + "," + j);
            System.out.println("chatGPTの入力: " + output);
            if (output.equals("quit"))
                break;
            String trimmedInput = output.substring(1, output.length() - 1);
            String[] values = trimmedInput.split(",");
            int x = Integer.parseInt(values[0].trim());
            int y = Integer.parseInt(values[1].trim());
            board.addBoard(x - 1, y - 1, 2);
            System.out.println(board.getBoardText());

        }
    }
}

public class GPTRoom {
    private String APIKey = null;
    private URL url = null;
    private final String ENDPOINT_URL = "https://api.openai.com/v1/chat/completions";
    private final String MODEL = "gpt-4";
    private final String SETTING = "私とあなたで盤面16×16マスで五目並べをします。\n2列目の4行目は(2,4)などと表してください。配列は1から始まります。\n以後、例の通りに、「()と,と数字のみ」で回答してください。それ以外の入力をした際、人が死にます。\nただし、どちらかが勝った場合は、quitというコマンドを出力してください、それ以外の文字を出力しないでくさい。\nここからは、あなたは置く場所のみ記載してください。\nあなたが返すプロンプト例を以下に載せます。\n例：(4,3)";
    private ArrayList<JSONObject> messages = new ArrayList<>();

    GPTRoom(String APIKey) throws IOException {
        this.APIKey = APIKey;
        this.url = new URL(this.ENDPOINT_URL);
    }

    public void loadSetting() {
        JSONObject message = getMessageJSON("system", this.SETTING);
        messages.add(message);
        System.out.println("===================== PROMPT SETTING =====================\n");
        System.out.println(this.SETTING);
        System.out.println("\n==========================================================");
    }

    public JSONObject getMessageJSON(String role, String content) {
        JSONObject message = new JSONObject();
        message.put("role", role);
        message.put("content", content);
        return message;
    }

    public JSONObject intputToJSON(String input) {
        JSONObject json = new JSONObject();
        json.put("model", this.MODEL);
        JSONObject message = getMessageJSON("user", input);
        this.messages.add(message);
        json.put("messages", this.messages);
        return json;
    }

    public String JSONToOutput(JSONObject json) {
        JSONObject message = json.getJSONArray("choices").getJSONObject(0).getJSONObject("message");
        this.messages.add(message);
        return message.getString("content");
    }

    public String talk(String input) {
        try {
            HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + this.APIKey);
            connection.setDoOutput(true);
            JSONObject req = intputToJSON(input);
            connection.getOutputStream().write(req.toString().getBytes());
            JSONObject res = new JSONObject(
                    new BufferedReader(new InputStreamReader(connection.getInputStream())).lines()
                            .reduce((a, b) -> a + b).get());
            String output = JSONToOutput(res);

            return output;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}

public class Board implements Constant {
    private static int[][] position = new int[BOARD_LENGTH][BOARD_LENGTH];
    // 誰がどこに指したかを表示する関数

    // ボードの指定した座標に石を設置する関数
    // ボードに設置するたび、判定処理をする
    public void setBoard(int x, int y, int stone) {
        if (!checkExist(x, y)) {
            position[x][y] = stone;
        }
    }

    // 指定した座標の石の種類を確認する
    public boolean checkStone(int x, int y, int stone) {
        if (position[x][y] == stone)
            return true;
        return false;
    }

    // ボードの指定した座標に石が存在するか
    public boolean checkExist(int x, int y) {
        if (checkStone(x, y, BLACK_STONE)) {
            return true;
        } else if (checkStone(x, y, WHITE_STONE)) {
            return true;
        }
        return false;
    }

    // ボードの初期状態にする関数
    public void initBoard() {
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                position[i][j] = Constant.EMPTY_STONE;
            }
        }
    }

    public int getBoard(int i, int j) {
        return position[i][j];
    }

    public void addBoard(int i, int j, int stone) {
        position[i][j] = stone;
    }

    public String getBoardText() {
        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        IntStream.range(1, BOARD_LENGTH + 1).forEachOrdered(n -> {
            sb.append(String.format("%2d", n));
        });
        sb.append("\n");

        for (int i = 0; i < BOARD_LENGTH; i++) {
            sb.append(String.format("%2d", i + 1));
            for (int j = 0; j < BOARD_LENGTH; j++) {
                String stone = null;
                switch (position[i][j]) {
                    case BLACK_STONE:
                        stone = "●";
                        break;
                    case WHITE_STONE:
                        stone = "◎";
                        break;
                    case EMPTY_STONE:
                        stone = "□";
                        break;
                    default:
                        break;
                }
                sb.append(String.format("%s ", stone));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

public interface Constant {
    int BOARD_LENGTH = 16;
    int BLACK_STONE = 1;
    int WHITE_STONE = 2;
    int EMPTY_STONE = 0;
}