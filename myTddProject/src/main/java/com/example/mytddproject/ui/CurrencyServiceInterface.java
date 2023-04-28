package com.example.mytddproject.ui;


import com.example.mytddproject.dto.DayCurrencyDto;
import com.example.mytddproject.services.CurrencyService;
import jakarta.xml.bind.ValidationException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CurrencyServiceInterface {

    private final CurrencyService currencyService;

    public CurrencyServiceInterface() {
        this.currencyService = new CurrencyService();
    }

    public void run() throws ValidationException {
        System.out.println("Вы вошли в программу 'Конвертер валют с возможностью предсказания курсов'!");
        while (true) {
            System.out.print("Выберите действие:\n" +
                    "\tвведите 1, если хотите продолжить" +
                    "\n\tвведите 2, если хотите выйти" +
                    "\nВАШ ВЫБОР:\t");
            Scanner scanner = new Scanner(System.in);
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод");
                continue;
            }
            switch (choice) {
                case 1:
                    runConverter();
                    break;
                case 2:
                    System.out.println("Вы вышли");
                    return;
                case default:
                    System.out.println("Неверный ввод");
            }
        }
    }

    public void runConverter() throws ValidationException {
        String sourceCurrencyCode = inSourceCurrencyCode();
        String resultCurrencyCode = inResultCurrencyCode(sourceCurrencyCode);
        double num = inNum();

        List<DayCurrencyDto> sourceCurrencyList = currencyService.getCurrencyForPeriod(sourceCurrencyCode);
        List<DayCurrencyDto> resultCurrencyList = currencyService.getCurrencyForPeriod(resultCurrencyCode);

//        TODO double todaySourceCurrencyValue = currencyService.getLastCurrencyValue(sourceCurrencyList);
//        TODO double todayResultCurrencyValue = currencyService.getLastCurrencyValue(resultCurrencyList);

        //TODO double resNum = currencyService.convertCurrency(todaySourceCurrencyValue, todayResultCurrencyValue, num);

        System.out.println("");
        //TODO System.out.println(num + " (" + sourceCurrencyCode + ") = " + resNum + " (" + resultCurrencyCode + ")");

        //TODO double change = currencyService.getCurrencyChange(resultCurrencyList);
        //TODO String message = currencyService.genCurrencyChangeMessage(change);
        //TODO System.out.println(message);
    }

    //Вывести в консоли варианты выбора валюты
    public void printCurrencyChoice() {
        int counter = 1;
        for (List<String> list: currencyService.currencyCodes) {
            System.out.println(String.format("\t%d - %s", counter++, list.get(0)));
        }
    }

    //Выбор исходной валюты
    public String inSourceCurrencyCode() {
        Scanner scanner = new Scanner(System.in);
        //Ввод исходной валюты
        while (true) {
            int index;
            System.out.println("Выберите исходную валюту:");
            printCurrencyChoice();
            try {
                index = Integer.parseInt(scanner.next());
                if (0 < index && index <= currencyService.currencyCodes.size()) {
                    return currencyService.currencyCodes.get(index - 1).get(1);
                } else {
                    System.out.println("Неверный индекс");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неверный ввод");
            }
        }
    }

    //Выбор исходной валюты
    public String inResultCurrencyCode(String sourceCurrencyCode) {
        Scanner scanner = new Scanner(System.in);
        //Ввод получаемой валюты
        while (true) {
            int index;
            System.out.println("Выберите получаемую валюту");
            printCurrencyChoice();
            try {
                index = Integer.parseInt(scanner.next());
                if (0 < index && index <= currencyService.currencyCodes.size()) {
                    String resultCurrencyCode = currencyService.currencyCodes.get(index - 1).get(1);
                    if (sourceCurrencyCode != resultCurrencyCode) {
                        return resultCurrencyCode;
                    } else {
                        System.out.println("Исходная и получаемая валюты не должны совпадать");
                    }
                } else {
                    System.out.println("Неверный индекс");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неверный ввод");
            }
        }
    }

    //Ввод суммы для конвертации
    public double inNum() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ввведите исходную сумму");
            try {
                double num = Double.parseDouble(scanner.next());
                if (num < 0) {
                    System.out.println("Сумма не должна быть меньше нуля");
                } else {
                    return num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод");
            }
        }
    }
}
