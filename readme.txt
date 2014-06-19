This is a technical task for the Betfair interview process. You will need to write a class
that allows the matching of Bets. It should take no more than 3 hours and is designed
to allow you to showcase your technical ability.

Betfair operates in a very similar way to a Stock Exchange. Instead of Buy and Sell orders for stocks, Betfair
has Back and Lay Bets, and instead of stocks Betfair have markets (see glossary for further explanations). Like a
Stock Exchange, Betfair matches Back bets to Lay bets made on the same market.

Your task is to create a simple Matching Engine that can match Back and Lay bets. The Matching Engine will accept a
data structure containing various back and lay bets and return another data structure than contains any matched bets
and any remaining bets that could not be matched. For the sake of the exercise, all bets will be of the same odds 
(2.0 or ‘evens’), and the same value: 10 GBP.

Scenario 1
Input is: 1 Back bet on Market A, 1 Lay bet on Market A
Output is: 1 Matched bet on Market A

Scenario 2
Input is: 1 Back bet on Market A, 1 Lay bet on Market B
Output is: No Matched bets, 1 Back bet on Market A, 1 Lay bet on Market B

Scenario 3
Input is: 1 Back bet on Market A, 1 Back bet on Market B
Output is: No Matched bets, 1 Back bet on Market A, 1 Back bet on Market B

Scenario 4
Input is: 2 Back bets on Market A, 1 Lay bet on Market A
Output is: 1 Matched bet on Market A, 1 Back bet on Market A

Scenario 5
Input is: 1 Back bet on Market A, 1 Lay bet on Market A, 1 Back bet on Market B, 1 Lay bet on Market B
Output is: 1 Matched bet on Market A, 1 Matched bet on Market B

Scenario 6
Input is: 2 Back bets on Market A, 1 Lay bet on Market A, 1 Back bet on Market B, 2 Lay bets on Market B
Output is: 1 Matched bet on Market A, 1 Matched bet on Market B, 1 Back bet on Market A, 1 Lay bet on Market B

A skeleton maven 3.0 project structure has been provided for you.

Dependencies for unit testing have been provided. You may need to add extra dependencies for any other
libraries you wish to use.

Setup
You will need to have Java (6.0 or greater) and Maven (3.0.x) installed on your computer

Glossary
Market - an event that a series of bets can be made on, e.g. the result of a football match or horse race
Back bet - a bet that an outcome will happen, e.g. that a football team will win a certain match
Lay bet - a bet that an outcome will not happen. It is the opposite of a back bet
Matched bet - a combination of a lay bet and a back bet that share the same price