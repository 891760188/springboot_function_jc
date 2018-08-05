package ye.guo.huang.jwt.common.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenCache {

	private static Map<String, Token> heap = new ConcurrentHashMap<String, Token>();

	private static Map<String, Token> removeHeap = new ConcurrentHashMap<String, Token>();

	public static Map<String, Token> getRemoveHeap() {
		return removeHeap;
	}

	public static Map<String, Token> getCurrentHeap() {
		return heap;
	}

	public static void setToken(String jwtStr, Token token) {
		heap.put(jwtStr, token);
	}

	public static Token getToken(String jwtStr) {
		return heap.get(jwtStr);
	}

	public static Token removeToken(String jwtStr) {
		Token token =  heap.remove(jwtStr);
		removeHeap.put(jwtStr, token);
		return token;
	}
}
