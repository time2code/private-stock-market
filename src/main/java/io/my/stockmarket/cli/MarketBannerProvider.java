/*
 * Copyright 2011-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.my.stockmarket.cli;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

/**
 * Market CLI banner
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MarketBannerProvider extends DefaultBannerProvider {

	public String getBanner() {
		return
		"                   \n" +
				" ____          _   \n" +
				"|    \\ ___ ___| |_ \n" +
				"|  |  | .'|  _| '_|\n" +
				"|____/|__,|_| |_,_|\n" +
				"                   \n" +
				"                   \n" +
				" _____         _   \n" +
				"|  _  |___ ___| |  \n" +
				"|   __| . | . | |  \n" +
				"|__|  |___|___|_|  \n" +
				"                   ";
	}

	public String getWelcomeMessage() {
		return "Dark Pool CLI. Type 'help' for available commands";
	}
}