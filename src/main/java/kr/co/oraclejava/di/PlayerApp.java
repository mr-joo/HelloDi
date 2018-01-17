package kr.co.oraclejava.di;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

public class PlayerApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PlayerDao dao = context.getBean(PlayerDao.class);

		Player player = new Player();

		Scanner scan = new Scanner(System.in);

		int num = 0;

		while (true) {
			System.out.println("\n1.선수 목록 출력\n2.선수 추가\n3.선수 수정\n4.선수 삭제\n5.종료");
			System.out.println();
			num = Integer.parseInt(scan.nextLine());

			switch (num) {
			case 1:
				List<Player> players = dao.getPlayerList();
				for (Player p : players) {
					System.out.println("id" + p.getId());
					System.out.println("name" + p.getName());
					System.out.println("age" + p.getAge());
					System.out.println("salary" + p.getSalary());
					System.out.println();
				}
				break;

			case 2:
				System.out.println("선수 이름을 입력하세요.");
				player.setName(scan.next());

				System.out.println("선수 나이를 입력하세요.");
				player.setAge(scan.next());

				System.out.println("선수 연봉을 입력하세요.");
				player.setSalary(scan.next());
				dao.insertPlayer(player);
				break;

			case 3:
					System.out.println("수정 할 선수의 id를 입력해주세요.");
					player.setId(scan.next());

					try {
						player = dao.getPlayer(player.getId());
					} catch (EmptyResultDataAccessException e) {
						System.out.println("없는 ID입니다.");
						continue;
					}
					
					System.out.println("선수 이름을 입력하세요.");
					player.setName(scan.next());

					System.out.println("선수 나이를 입력하세요.");
					player.setAge(scan.next());

					System.out.println("선수 연봉을 입력하세요.");
					player.setSalary(scan.next());

					dao.updatePlayer(player);		
				break;

			case 4:
				try {
					System.out.println("삭제 할 선수의 id를 입력해주세요.");
					player.setId(scan.next());

					player = dao.getPlayer(player.getId());

					dao.deletePlayer(player);
				} catch (Exception e) {
					System.out.println("없는 ID입니다.");
				}
				break;

			case 5:
				return;
			default:
				System.out.println("없는 번호입니다.");
				break;
			}
		}

	}

}
