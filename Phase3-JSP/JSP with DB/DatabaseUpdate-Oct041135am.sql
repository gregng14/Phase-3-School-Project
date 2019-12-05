-- Changing the Agencies table information
UPDATE Agencies
SET AgncyCity='Calgary'
WHERE AgencyId=1;
UPDATE Agencies
SET AgncyAddress='110 Okotoks Drive', AgncyCity='Okotoks', AgncyPostal='T1S 1G1'
WHERE AgencyId=2;

-- Adding the FeeId column in the Bookings table
ALTER TABLE Bookings
ADD FeeId varchar(50);

-- Package ID assigning in PHPMyAdmin MySQL DB
UPDATE `Bookings` SET `PackageId`=1 WHERE `BookingId`%4=0;
UPDATE `Bookings` SET `PackageId`=2 WHERE `BookingId`%4=1;
UPDATE `Bookings` SET `PackageId`=3 WHERE `BookingId`%4=2;
UPDATE `Bookings` SET `PackageId`=4 WHERE `BookingId`%4=3;

-- Update the Fees to be just for Booking Fee Number
UPDATE `Bookings` SET FeeId='BK';

-- Adding the indexing to the FeeId
ALTER TABLE `Bookings` ADD INDEX(`FeeId`);

-- Changing the relation links
ALTER TABLE `Bookings` ADD CONSTRAINT `fee_id` FOREIGN KEY (`FeeId`) REFERENCES `travelexperts`.`fees`(`FeeId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Create UserId and Password columns in the Customers table
ALTER TABLE Customers
ADD (UserId varchar(50),
Password varchar(50));

-- Unique userId
ALTER TABLE `Customers` ADD UNIQUE(`UserId`);

-- Set the UserId and Password for the Customers in the new columns
UPDATE Customers SET UserId=CONCAT('user', CustomerId), Password=CONCAT('pass', CustomerId);