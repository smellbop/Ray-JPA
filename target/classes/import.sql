--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
insert into Member (id, name, email, phone_number) values (0, 'John Davidson', 'john.smith@mailinator.com', '2125551212') ;

insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1001, 'SR', 'INPROG','MIAMI',3);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1011, 'SR', 'INPROG','MIAMI',3);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1101, 'SR', 'INPROG','MIAMI',3);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1021, 'SR', 'PENDING','MIAMI',3);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1022, 'SR', 'PENDING','MIAMI',4);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1023, 'SR', 'QUEUED','MIAMI',4);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1045, 'SR', 'QUEUED','MIAMI',4);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1078, 'SR', 'INPROG','CNR',3);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1080, 'SR', 'PENDING','CNR',3);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1081, 'SR', 'INPROG','CNR',3);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1097, 'SR', 'PENDING','FES',3);
insert into Ticket (ticketid, class, status, siteid, internalpriority) values (1099, 'SR', 'INPROG','FES',5);
