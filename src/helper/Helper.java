package helper;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Helper {

	public static void println(String string) {
		System.out.println(string);
	}
	public static void print(String string) {
		System.out.print(string);
	}

	private static Scanner scanner = new Scanner(System.in);
	public static int inputInt(String string) {
		System.out.print("input "+string+" : ");
		return scanner.nextInt();
	}
	public static double inputDouble() {
		return scanner.nextDouble();
	}
	public static String inputString(String string) {
		System.out.print("input "+string+" : ");
		return scanner.next();
	}

	private static Random rand = new Random();
	public static int random(int from,int to) {
		return rand.nextInt(to-from)+from;
	}
	public static int random(int to) {
		return rand.nextInt(to);
	}

        
        
        public static Map<String, Object> getNonNullProperties(final Object thingy) {
            final Map<String, Object> nonNullProperties = new TreeMap<String, Object>();
            try {
                final BeanInfo beanInfo = Introspector.getBeanInfo(thingy
                        .getClass());
                for (final PropertyDescriptor descriptor : beanInfo
                        .getPropertyDescriptors()) {
                    try {
                        final Object propertyValue = descriptor.getReadMethod()
                                .invoke(thingy);
                        if (propertyValue != null) {
                            nonNullProperties.put(descriptor.getName(),
                                    propertyValue);
                        }
                    } catch (final IllegalArgumentException e) {
                        // handle this please
                    } catch (final IllegalAccessException e) {
                        // and this also
                    } catch (final InvocationTargetException e) {
                        // and this, too
                    }
                }
            } catch (final IntrospectionException e) {
                // do something sensible here
            }
            return nonNullProperties;
        }
}