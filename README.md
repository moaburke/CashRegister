# Cash Register System

## Description

The Cash Register System is a Java-based console application for managing articles and sales. It allows users to add and remove articles, register sales, and view articles and sales data. The application provides a menu-driven interface for interacting with the system.

## Features

- Insert articles with random values
- Remove articles
- Display a list of articles
- Register sales of articles
- Display sales data and sorted order history

## Usage
Upon running the application, you will be presented with a menu offering various options:

1. Insert Articles: Add new articles to the inventory.
2. Remove an Article: Remove an existing article from the inventory.
3. Display Articles: View the list of articles in the inventory.
4. Register a Sale: Register a sale of an article and update the sales data.
5. Display Sales Data: View the sales data, including date, article number, quantity, and total price.
6. Sort and Display Sales Data: View the sales data sorted by article number.

To exit the program, select the 7 option or type q.

## Example

```plaintext
1. Insert articles
2. Remove an article
3. Display a list of articles
4. Register a sale
5. Display order history
6. Sort and display order history table
q. Quit
Your choice: 1

How many articles do you want to add? 5
5 articles added successfully.

1. Insert articles
2. Remove an article
3. Display a list of articles
4. Register a sale
5. Display order history
6. Sort and display order history table
q. Quit
Your choice: 3

----------------------------------
 # Article Table
----------------------------------
Article    Quantity           Price
1000       3                   955
1001       4                   822
1002       7                   402
1003       7                   835
1004       2                   714

1. Insert articles
2. Remove an article
3. Display a list of articles
4. Register a sale
5. Display order history
6. Sort and display order history table
q. Quit
Your choice: 4

What article do you want to buy?
1003
How many items of article 1003 do you want to buy?
5

1. Insert articles
2. Remove an article
3. Display a list of articles
4. Register a sale
5. Display order history
6. Sort and display order history table
q. Quit
Your choice: 4

What article do you want to buy?
1000
How many items of article 1000 do you want to buy?
10
Not enough items. Please enter another amount.
3 items of article 1000 are avalible for purchase.
3

1. Insert articles
2. Remove an article
3. Display a list of articles
4. Register a sale
5. Display order history
6. Sort and display order history table
q. Quit
Your choice: 5

----------------------------------
 # Sales Data
----------------------------------
Date                 Article   Quantity      Total
09:34 2024-09-20     1003             5       4175
09:34 2024-09-20     1000             3       2865

1. Insert articles
2. Remove an article
3. Display a list of articles
4. Register a sale
5. Display order history
6. Sort and display order history table
q. Quit
Your choice: 6

----------------------------------
 # Sorted order history
----------------------------------
Date                 Article   Quantity      Total
09:34 2024-09-20     1000             3       2865
09:34 2024-09-20     1003             5       4175

1. Insert articles
2. Remove an article
3. Display a list of articles
4. Register a sale
5. Display order history
6. Sort and display order history table
q. Quit
Your choice: q

Goodbye!
