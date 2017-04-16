package com.tnzyq;

/**
 * 扑克牌类
 * @author 霍佳彤
 *
 */
public class Card {

	//牌面值数组
	String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	//花色数组
	String[] suits = {"黑桃", "红桃", "梅花", "方块"};
	//牌的总数
	int maxCards = faces.length * suits.length;
	//这张牌的点数
	int value = 0;
	
	//构造方法
	public Card() {
		value = 0;//用0创建一章默认纸牌
	}
	
	//构造方法,创建第n张牌
	public Card(int n) {
		if(n < 0 || n > this.maxCards){
			System.out.println("初始化纸牌错误，退出游戏！");
			System.exit(0);
		}
		value = n;
	}
	
	//返回表示纸牌面值的字符串
	public String getFace() {
		return faces[value % faces.length];
	}
	
	//返回表示纸牌花色的字符串
	public String getSuit() {
		return suits[value % suits.length];
	}
	
	//返回当前纸牌的花色及面值
	public String toString() {
		return getSuit() + getFace();
	}
}