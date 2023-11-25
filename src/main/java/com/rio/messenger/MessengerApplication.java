package com.rio.messenger;

import com.rio.messenger.entity.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.*;
import java.util.stream.Collectors;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class MessengerApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MessengerApplication.class, args);
		Message m1 = new Message("a","b","hello");
		Message m2 = new Message("a","b","world");
		Message m3 = new Message("c","b","fuzz");
		Message m4 = new Message("d","b","bizz");
		Message m5 = new Message("d","b","kell");
		List<Message> m = new ArrayList<>();
		m.add(m1);
		m.add(m2);
		m.add(m3);
		m.add(m4);
		m.add(m5);

		Map<String,List<Message>> map1 = m.stream().collect(
				Collectors.groupingBy(Message::getFrom)
		);
		for (Map.Entry<String,List<Message>> e : map1.entrySet()){
			List<String> messages = e.getValue().stream().map(Message::getMsg).collect(Collectors.toList());
		}
		System.out.println(map1);

	}

}
