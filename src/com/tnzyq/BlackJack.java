package com.tnzyq;

import java.util.Scanner;

/**
 * 游戏-黑杰克（21点）
 * 
 * @author 霍佳彤
 * 
 */
public class BlackJack {

	Player player;// 玩家
	Player computer;// 电脑
	Dealer deck;// 获取一副牌
	Scanner input;

	/**
	 * 构造方法，输出菜单并完成所有方法调用
	 */
	public BlackJack() {
		player = new Player();
		computer = new Player();
		deck = new Dealer();
//		String str = null;
		input = new Scanner(System.in);

		//游戏主循环
		while (true) {
			player.playAgain();// 清空玩家手中的牌
			computer.playAgain();//清空电脑手中的牌
			
			// 初始化游戏界面
			System.out.println("※※※※※※※※※※※※※※※※※※※※※※");
			System.out.println("※\t\t\t\t\t※");
			System.out.println("※\t\t黑杰克(21点)\t\t※");
			System.out.println("※\t\t\t\t\t※");
			System.out.println("※\t\t作者：霍佳彤\t\t※");
			System.out.println("※\t\t\t\t\t※");
			System.out.println("※※※※※※※※※※※※※※※※※※※※※※");
			
			System.out.println("\n\t\t1.开始游戏");
			System.out.println("\n\t\t2.查看比分");
			System.out.println("\n\t\t3.结束游戏");
			
			System.out.println("请选择：");
			int choose = input.nextInt();
			switch(choose){
				case 1:
					this.start();//开始游戏
					break;
				case 2:
					this.checkStore();//查看比分
					break;
				case 3:
					System.exit(0);//结束游戏
					break;
				default:
					System.out.println("输入错误，请重新选择！");	
					break;
			}
		
		}
	}

	/**
	 * 游戏开始，在这里完成第一把的投注于发出第一张牌， 然后玩家选择是投注加倍金额，发下一张牌还是PASS或者看牌
	 */
	public void start() {
		int money = 20;// 投注金额
		int yourChoice;// 用户选择
		System.out.println("请输入你要投注的金额（最小面值20）：");
		money = input.nextInt();
		if (money < 20) {
			System.out.println("您输入的金额过小，系统自动将投注金额设置为20");
			money = 20;// 强制最小投注为20
		}
		if(money > player.cash){
			System.out.println("您投注的金额超过你拥有的金额，系统自动投注您的最大金额");
			money = player.cash;
		}
		System.out.println();
		System.out.println("开始发牌。。。。。");
		player.setHand(deck.getCard());
		computer.setHand(deck.getCard());
		do {
			if(player.cash < 0){
				System.out.println("你没钱了，滚吧!");
				System.exit(0);
			}
			System.out.println("您得到的牌是：" + player.getHandList());
			System.out.println("您的选择：1.金额加倍并看牌；2.PASS(重开一局)；3.再要一张；4.开牌；5.退回上级菜单");
			yourChoice = input.nextInt();
			boolean replay = false;
			switch (yourChoice) {
			case 1:
				yourDouble(money);
				break;
			case 2:
//				yourPass(money);
				replay = true;//选pass时重开一局，不结算金额
				break;
			case 3:
				getCardAgain();
				break;
			case 4:
				winOrLose(money);
				break;
			case 5:
				break;
			default:
				break;
			}
			if(replay){
				break;//选pass是重开一局
			}
		} while (yourChoice != 5);
	}

	/**
	 * 查看玩家得分
	 */
	public void checkStore() {
		System.out.println("你胜利了" + player.winNumber +"次");
		System.out.println("你失败了" + player.loseNumber +"次");
		System.out.println("你还有" + player.cash + "货币单位");
	}

	/**
	 * 玩家投注加倍
	 * 
	 * @param money
	 */
	public void yourDouble(int money) {
		money *= 2;// 投注金额加倍

		// 判断输赢
		winOrLose(money);
	}

	/**
	 * 点数计算
	 * 
	 * @param player
	 *            游戏参与者
	 * @return
	 */
	public int computePoint(Player player) {
		
		int count = 0;
		int countA = 0;//记录手中牌A的张数
		Card[] cards = player.getCard().getCards();
		for(int i = 0; i < cards.length; i++){
			if(cards[i] != null){
				if((cards[i].getFace().equals("J")) || (cards[i].getFace().equals("Q")) || (cards[i].getFace().equals("K"))){
					count += 10;
				}else if(!cards[i].getFace().equals("A")){
					count += Integer.valueOf(cards[i].getFace());
				}else if(cards[i].getFace().equals("A")){
					countA++;
				}
			}
		}
		// 由于A的积分特殊,需要单独运算
		//循环A的数量次，先假设全部A都算11，每爆点一次，就把一个A记作1
		for(int i = countA; i > 0; i--){
			if((count + countA*11) > 21){
				count += 1;
			}else{
				count += 11;
			}
		}
		
//		for(int i = 0; i < cards.length; i++){
//			if(cards[i] != null){
//				if(cards[i].getFace().equals("A")){
//					if(count + 11 > 21){
//						count += 1;
//					}else{
//						count += 11;
//					}
//				}
//			}
//		}
		
		
		return count;
	}

	/**
	 * 选择pass后计算输赢
	 * 
	 * @param money
	 */
	public void yourPass(int money) {
		// 判断输赢
		winOrLose(money);
	}

	/**
	 * 再发一张
	 */
	public void getCardAgain() {
		player.setHand(deck.getCard());
	}

	/**
	 * 选择开牌后判断输赢
	 * 
	 * @param money
	 */
	public void winOrLose(int money) {
		int playerPoint = computePoint(player);// 获得玩家点数
		int comPoint = comPlayer();// 获得电脑点数

		//输赢判断(根据输赢更新'钱'与'输赢次数')
		if(playerPoint > 21 && comPoint >21){
			System.out.println("你的得分：" + playerPoint + ",电脑得分：" + comPoint + ",都爆点了，平局！");
		}else if(playerPoint == comPoint){
			System.out.println("你的得分：" + playerPoint + ",电脑得分：" + comPoint + ",得分相同，平局！");
		}else if(playerPoint > 21 ){
			System.out.println("你的得分：" + playerPoint + ",电脑得分：" + comPoint + ",你爆点了，你输了！");
			player.youLose(money);
		}else if(comPoint > 21){
			System.out.println("你的得分：" + playerPoint + ",电脑得分：" + comPoint + ",电脑爆点了，你赢了！");
			player.youWin(money);
		}else if(playerPoint > comPoint){
			System.out.println("你的得分：" + playerPoint + ",电脑得分：" + comPoint + ",你赢了！");
			player.youWin(money);
		}else if(playerPoint < comPoint){
			System.out.println("你的得分：" + playerPoint + ",电脑得分：" + comPoint + ",你输了！");
			player.youLose(money);
		}
		
		
		player.hand.clear();//清空玩家及电脑手中的牌
		computer.hand.clear();
		System.out.println();//开始下一局
		System.out.println("开始发牌。。。。。");
		player.setHand(deck.getCard());
		computer.setHand(deck.getCard());
	}

	/**
	 * 电脑玩家的控制
	 * 
	 * @return
	 */
	public int comPlayer() {
		int comPoint = this.computePoint(computer);
		System.out.println("电脑开始：" + computer.getHandList());
		while (comPoint != 0 && comPoint <= 18 && computer.getSize() < 3) {// 人工智能：如果电脑手上的点数小于18点就发牌
			computer.setHand(deck.getCard());// 再发一张牌给电脑

			System.out.println("再发一张,电脑手中：" + computer.getHandList());
			comPoint = computePoint(computer);// 重新计算分数
		}
		return comPoint;
	}
	
	//程序入口
	public static void main(String[] args) {
		new BlackJack();
	}
}
