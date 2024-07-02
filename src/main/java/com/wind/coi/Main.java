package com.wind.coi;

import com.wind.coi.entity.Character;
import com.wind.coi.service.UpgradeSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author hsc
 * @date 2024/6/27 13:54
 */
public class Main {

    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入角色名称: ");
        String characterName = scanner.nextLine();
        Character player = new Character(characterName);

        // 假设每秒钟获取一次经验值，1000毫秒=1秒
        long timeUnitInMillis = 1000;

        Random random = new Random();
        // 模拟60秒的游戏时间
        int year = random.nextInt(1000);
        LocalDate startDate = LocalDate.of(year, 1, 1);
        int day = 1;
        while (true) {
            System.out.println(startDate.format(df));
            // 计算本次获得的经验值，这里为了简化，假设每次获得的经验值是随机的
            int gainedExp = random.nextInt(50) + 1;
            UpgradeSystem.grantExperience(player, gainedExp);
            TimeUnit.MILLISECONDS.sleep(timeUnitInMillis);
            startDate = startDate.plusDays(day);
        }
    }
}
