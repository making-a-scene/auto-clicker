import java.awt.*;
import java.awt.event.InputEvent;

public class AutoClicker {
    static final int WAITING_TIME = 10000; // ms, 좌표 위치 입력받기까지의 대기 시간
    static final int DELAY = 1000; // ms, 좌표와 좌표 사이의 클릭 딜레이
    static final int MAX_CLICKING = 10; //  반복할 전체 횟수
    static final int NUM_OF_COORDINATES = 3; // 한 번 실행에 클릭해야 할 좌표 개수
    static final int[][] COORDINATES = new int[NUM_OF_COORDINATES][2];

    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            
            for (int i = 0; i < NUM_OF_COORDINATES; i++) {
                waitBeforeGetCoord();
                COORDINATES[i] = new int[2];
                COORDINATES[i] = getMouseCoordinates();
            }

            for (int count = 0; count < MAX_CLICKING; count++) {
                for (int i = 0; i < NUM_OF_COORDINATES; i++) {
                    clickOp(COORDINATES[i], robot);
                    robot.delay(DELAY);
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static void clickOp(int[] coordinates, Robot robot) {
        robot.mouseMove(coordinates[0], coordinates[1]);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private static int[] getMouseCoordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = (int) MouseInfo.getPointerInfo().getLocation().getX();
        coordinates[1] = (int) MouseInfo.getPointerInfo().getLocation().getY();
        return coordinates;
    }

    private static void waitBeforeGetCoord() {
        int waitingSec = WAITING_TIME / 1000;
        System.out.println(WAITING_TIME / 1000 + "초 후에 마우스 커서의 위치를 읽습니다.\n자동 클릭하고자 하는 위치에 마우스 커서를 위치시켜주세요.");
        try {
            for (; waitingSec > 0; waitingSec--) {
                System.out.println(waitingSec);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    