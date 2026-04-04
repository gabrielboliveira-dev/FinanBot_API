CREATE TABLE cash_flow_predictions (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    target_date DATE NOT NULL,
    predicted_balance NUMERIC(19, 2) NOT NULL,
    CONSTRAINT fk_cash_flow_predictions_user FOREIGN KEY (user_id) REFERENCES users(id)
);