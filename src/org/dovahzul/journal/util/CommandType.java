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
	UPDATE_BID, //request for selling
	UPDATE_ASK, //request for buying
	SPREAD_BEST_BID, //spread, display current state
	SPREAD_BEST_ASK, //spread, display current state
	SPREAD_SIZE_BY_PRICE, // spread, display current size of stock by giving price
	

}
