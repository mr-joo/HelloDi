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
			System.out.println("\n1.���� ��� ���\n2.���� �߰�\n3.���� ����\n4.���� ����\n5.����");
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
				System.out.println("���� �̸��� �Է��ϼ���.");
				player.setName(scan.next());

				System.out.println("���� ���̸� �Է��ϼ���.");
				player.setAge(scan.next());

				System.out.println("���� ������ �Է��ϼ���.");
				player.setSalary(scan.next());
				dao.insertPlayer(player);
				break;

			case 3:
					System.out.println("���� �� ������ id�� �Է����ּ���.");
					player.setId(scan.next());

					try {
						player = dao.getPlayer(player.getId());
					} catch (EmptyResultDataAccessException e) {
						System.out.println("���� ID�Դϴ�.");
						continue;
					}
					
					System.out.println("���� �̸��� �Է��ϼ���.");
					player.setName(scan.next());

					System.out.println("���� ���̸� �Է��ϼ���.");
					player.setAge(scan.next());

					System.out.println("���� ������ �Է��ϼ���.");
					player.setSalary(scan.next());

					dao.updatePlayer(player);		
				break;

			case 4:
				try {
					System.out.println("���� �� ������ id�� �Է����ּ���.");
					player.setId(scan.next());

					player = dao.getPlayer(player.getId());

					dao.deletePlayer(player);
				} catch (Exception e) {
					System.out.println("���� ID�Դϴ�.");
				}
				break;

			case 5:
				return;
			default:
				System.out.println("���� ��ȣ�Դϴ�.");
				break;
			}
		}

	}

}
