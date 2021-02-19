/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.authenticator;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Sebas663
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorControllerTest {

	@Test
	public void mockTest() {
		ArrayList<String> arrayList = mock(ArrayList.class);
		arrayList.add("Object 1");
		arrayList.add("Object 2");
		arrayList.add("Object 3");
		System.out.println(arrayList.size());
	}
}
