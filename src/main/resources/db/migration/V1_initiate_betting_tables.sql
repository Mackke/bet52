-- Create the Game table
CREATE TABLE game (
                      id VARCHAR(36) PRIMARY KEY,
                      name VARCHAR(255),
                      prediction VARCHAR(255),
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      game_coupon_id VARCHAR(16)
);

-- Create the GameCoupon table
CREATE TABLE game_coupon (
                            id VARCHAR(36) PRIMARY KEY,
                            coupon_name VARCHAR(255),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


ALTER TABLE game
ADD CONSTRAINT fk_game_coupon
FOREIGN KEY (game_coupon_id)
REFERENCES game_coupon(id);