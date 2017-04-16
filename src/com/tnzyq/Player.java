package com.tnzyq;

/**
 * 玩家类
 * @author 霍佳彤
 *
 */
public class Player {

	int winNumber;//赢的次数
	int loseNumber;//输的次数
	int cash;//玩家手中的现金
	CardsInHand hand;//玩家手中的牌
	
	/**
	 * 构造方法，初始化玩家信息
	 */
	public Player() {
		winNumber = 0;
		loseNumber = 0;
		hand = new CardsInHand();
		cash = 10000;
	}
	
	/**
	 * 获取玩家手中牌的列表
	 * @return 牌列表
	 */
	public String getHandList() {
		return hand.toString();
	}
	
	/**
	 * 获取玩家手中牌的张数
	 * @return
	 */
	public int getSize() {
		
		return hand.getSize();
	}
	
	/**
	 * 接受发来的牌
	 * @param card 
	 */
	public void setHand(Card card) {
		hand.setCard(card);
	}
	
	/**
	 * 胜利一局
	 */
	public void youWin(int money) {
		cash += money;
		winNumber++;
	}
	
	/**
	 * 再玩一局，清空玩家手中的牌
	 */
	public void playAgain() {
		hand = new CardsInHand();
	}
	
	/**
	 * 输了一局
	 */
	public void youLose(int money) {
		cash -= money;
		loseNumber++;
	}
	
	public CardsInHand getCard(){
		return hand;
	}
}
