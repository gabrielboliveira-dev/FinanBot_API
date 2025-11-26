package com.finanbot.infra.nlu;

import com.finanbot.core.usecase.dto.NluResult;
import com.finanbot.core.usecase.port.NluGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegexNluAdapter implements NluGateway {

    private final Pattern EXPENSE_PATTERN = Pattern.compile("(?i)gastei\\s+(\\d+([.,]\\d{1,2})?)\\s+(?:com|em|no|na)\\s+(.+)");

    @Override
    public NluResult predict(String text) {
        Matcher matcher = EXPENSE_PATTERN.matcher(text);

        if (matcher.find()) {
            String amountStr = matcher.group(1).replace(",", ".");
            BigDecimal amount = new BigDecimal(amountStr);

            String description = matcher.group(3);

            String category = "OUTROS";
            if (description.contains("almoço") || description.contains("comida")) category = "ALIMENTACAO";
            if (description.contains("uber") || description.contains("gasolina")) category = "TRANSPORTE";

            return new NluResult("create_transaction", amount, description, category);
        }

        return new NluResult("unknown", null, null, null);
    }
}