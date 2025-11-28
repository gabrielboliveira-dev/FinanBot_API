ALTER TABLE users ADD COLUMN telegram_chat_id VARCHAR(50);
CREATE INDEX idx_users_telegram_chat_id ON users(telegram_chat_id);