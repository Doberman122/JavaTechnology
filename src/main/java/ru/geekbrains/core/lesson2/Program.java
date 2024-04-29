package ru.geekbrains.core.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {


    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final int WIN_COUNT = 4;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static char[][] field;


    public static void main(String[] args) {
        while (true){
            initialize();
            printField();
            while (true){
                humanTurn();
                printField();
                if (checkState(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (checkState(DOT_AI, "Победил компьютер!"))
                    break;
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }

    }

    /**
     * Инициализация объектов игры
     */
    static void initialize(){
        fieldSizeX = 3;
        fieldSizeY = 3;
        field = new char[fieldSizeX][fieldSizeY];
        for(int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать текущего состояния игрового поля
     */
    static void printField() {
        // Печать верхней границы поля
        System.out.print("+");
        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print("--");
        }
        System.out.println("+");

        // Печать содержимого поля
        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print("|");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(" " + field[x][y] + " ");
            }
            System.out.println("|");
        }

        // Печать нижней границы поля
        System.out.print("+");
        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print("--");
        }
        System.out.println("+");
    }


    /**
     * Ход игрока (человека)
     */
    static void humanTurn(){
        int x;
        int y;
        do{
            System.out.println("Введите координаты хода X и Y\n(от 1 до 3) через пробел: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }


    /**
     * Проверка, является ли ячейка игрового поля пустой
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y) {
        char cellValue = getFieldCellValue(x, y);
        return cellValue == DOT_EMPTY;
    }

    static char getFieldCellValue(int x, int y) {
        if (isCellValid(x, y)) {
            return field[x][y];
        } else {
            // Возвращаем некорректное значение, чтобы указать на ошибку
            return '\0';
        }
    }

    /**
     * Проверка валидности координат хода
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y) {
        boolean withinXBounds = x >= 0 && x < fieldSizeX;
        boolean withinYBounds = y >= 0 && y < fieldSizeY;
        return withinXBounds && withinYBounds;
    }



    /**
     * Ход игрока (компьютера)
     */
    static void aiTurn(){
        int x;
        int y;
        do{
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка на ничью
     * @return
     */
    static boolean checkDraw(){
        for(int x = 0; x < fieldSizeX; x++){

            for (int y = 0; y < fieldSizeY; y++){
               if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    /**
     * TODO: Переработать в рамках домашней работы
     * Метод проверки победы
     * @param dot фишка игрока
     * @return
     */
    static boolean checkWin(char dot) {
        // Проверка по горизонталям
        for (int y = 0; y < fieldSizeY; y++) {
            if (checkLine(dot, 0, y, 1, 0, fieldSizeX)) return true;
        }

        // Проверка по вертикалям
        for (int x = 0; x < fieldSizeX; x++) {
            if (checkLine(dot, x, 0, 0, 1, fieldSizeY)) return true;
        }

        // Проверка по диагонали (слева сверху вправо вниз)
        if (checkLine(dot, 0, 0, 1, 1, Math.min(fieldSizeX, fieldSizeY))) return true;

        // Проверка по диагонали (слева снизу вправо вверх)
        if (checkLine(dot, 0, fieldSizeY - 1, 1, -1, Math.min(fieldSizeX, fieldSizeY))) return true;

        return false;
    }

    static boolean checkLine(char dot, int startX, int startY, int dx, int dy, int len) {
        for (int i = 0; i < len; i++) {
            int x = startX + i * dx;
            int y = startY + i * dy;
            if (!isCellValid(x, y) || field[x][y] != dot) return false;
        }
        return true;
    }


    static boolean checkWinV2(char dot, int win){
        for(int x = 0; x < fieldSizeX; x++){

            for (int y = 0; y < fieldSizeY; y++){
                //if (check1 == true || check2())
            }
        }
        return false;
    }

    static boolean check1(int x, int y, char dot, int win){
        return false;
    }

    static boolean check2(int x, int y, char dot, int win){
        return false;
    }

    static boolean check3(int x, int y, char dot, int win){
        return false;
    }

    static boolean check4(int x, int y, char dot, int win){
        return false;
    }

    /**
     * Проверка состояния игры
     * @param dot фишка игрока
     * @param s победный слоган
     * @return
     */
    static boolean checkState(char dot, String s){
        if (checkWin(dot)){
            System.out.println(s);
            return true;
        }
        else if (checkDraw()){
            System.out.println("Ничья!");
            return true;
        }
        return false; // Игра продолжается
    }

}
