package com.tnzyq;
/**
 * 手中牌类
 * @author 霍佳彤
 *
 */
public class CardsInHand {

	Card[] cards = new Card[22];//玩家最多可以拿22张牌就会爆分
	
	/**
	 * 接受一张发牌
	 * @param card 一张牌
	 */
	public void setCard(Card card) {
		for(int i = 0; i < 22; i++){
			if(cards[i] == null){
				cards[i] = card;
				break;
			}
		}
	}
	
	/**
	 * 清空玩家手中的牌
	 */
	public void clear() {
		cards = new Card[22];
	}
	
	/**
	 * 返回玩家手中的牌张数
	 * @return
	 */
	public int getSize() {
		int count = 0;
		
		for(int i = 0; i < 22; i++){
			if(cards[i] != null){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 返回玩家手中的牌列表
	 */
	public String toString() {
		
		String hand = "";
		//生成牌列表字符
		
		for(int i = 0; i < 22; i++){
			if(cards[i] != null){
				hand += cards[i] + " ";
			}
		}
		return hand;
	}
	
	public Card[] getCards(){
		return cards;
	}
}
