package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class PackObject<T> {
	Class<T> c;

	public static <T> T getObject(HttpServletRequest request, Class<T> c) {

		T t = null;
		try {
			t = c.newInstance(); // ʵ������������
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}

		@SuppressWarnings("rawtypes")
		Enumeration e = request.getParameterNames(); // �����������

		Method[] methods = c.getDeclaredMethods(); // ������������з���

		// ���ݶ����set�����Ĳ�������ȥ�������ֵ����Ӧת��
		while (e.hasMoreElements()) {
			String paramName = e.nextElement().toString();
			String setParamName = reverseParamName(paramName); //����������ת����set�������֣��磺id ת���� setId

			for (Method method : methods) {
				if (setParamName.equals(method.getName())) {
					try {
						Class<?> paramType = (method.getParameterTypes())[0]; //�õ�set������������
						String value = request.getParameter(paramName);
						adapter(t, method, paramType, value); //ͨ����������ֵע���POJO����
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						e1.printStackTrace();
					} catch (SecurityException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return t;
	}

	private static String reverseParamName(String paramName) {
		char firstChar = paramName.charAt(0);
		char toUpper = Character.toUpperCase(firstChar);
		String setParamName = "set" + String.valueOf(toUpper) + paramName.substring(1);
		return setParamName;
	}

	private static <T> void adapter(T t, Method method, Class<?> paramType, String value) throws IllegalAccessException, InvocationTargetException {
		if (paramType == String.class) {
			method.invoke(t, value);
		} else if (paramType == Integer.class || paramType == int.class) {
			method.invoke(t, Integer.parseInt(value));
		} else if (paramType == Long.class || paramType == long.class) {
			method.invoke(t, Long.parseLong(value));
		} else if (paramType == Boolean.class || paramType == boolean.class) {
			method.invoke(t, Boolean.parseBoolean(value));
		} else if (paramType == Short.class || paramType == short.class) {
			method.invoke(t, Short.parseShort(value));
		} else if (paramType == Float.class || paramType == float.class) {
			method.invoke(t, Float.parseFloat(value));
		} else if (paramType == Double.class || paramType == double.class) {
			method.invoke(t, Double.parseDouble(value));
		} else if (paramType == Character.class || paramType == char.class) {
			char[] cs = value.toCharArray();
			if (cs.length > 1) {
				throw new IllegalArgumentException("��������̫��");
			}
			method.invoke(t, value.toCharArray()[0]);
		}
	}
}