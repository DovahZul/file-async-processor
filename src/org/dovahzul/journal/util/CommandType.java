package org.dovahzul.journal.util;

/**
 * 
 * 
 *  samles:
	u,<price>,<size>,bid
	u,<price>,<size>,ask
	q,best_bid
	q,best_ask
	q,size, <price>
	o,buy, <size>  for minimum price
	o,sell,<size>  for maximum price
 * @author dovahzul
 *
 */
public enum CommandType {
	UNDEFINED,
	UPDATE_BID, //request for buying by client
	UPDATE_ASK, //request for selling by supplier
	SHOW_BEST_BID, //display current state
	SHOW_BEST_ASK, //display current state
	SHOW_SIZE_BY_PRICE, // spread, display current size of stock by giving price
	BUY,
	SELL,
	

}
