The Finance Helper!

Background:
I am a finance enthusaist, I look for all the new tools that can help me in my fiancial journey. When I think about people's number one issue when it comes to finance, its not knowing the ratio of what you are bringing in vs what you have going out income wise. This brings me to the word that will bring you closer to fikancial freedom, thats MARGIN.

What is Margin?
Margin is the remainder of money you have left over post all of your expenses. I like to call this free money. Money that is not occupied with a certain task or responsibility. Knowing your marginal numbers allows you to stay on track with your monthly spending and quickly know if something is afforadable or not.

The Finance Helper:

PURPOSE:
I developed this application to allow you to quickly assess your finances and determine your margin. This application will give you a complete breakdown from your inputs, leaving you with your marginal percentages and marginal amount in dollars. Also calcukating your expenses total so you can see the full picture.

Functionalities:
1 - Calculate margin
2 - Ability to view saved reports
3 - Strategy selection {Injecting savings goal, or Tax percentage to see true margin. }
4 - Export report in CSV

Project side chat:
In this project I was able to implement 8 design patterns

Strategy:
MarginCalculatorService holds a swappable MarginStrategy. Three implementations: StandardMarginStrategy, TaxAdjustedMarginStrategy, and SavingsGoalMarginStrategy.

Observer:
After each calculation, the service notifies all registered MarginObserver implementations: SummaryObserver, LowMarginObserver, and NegativeMarginObserver.

Decorator:
LoggingObserverDecorator and ThrottledObserverDecorator both wrap any MarginObserver, adding timestamped logging and cooldown throttling respectively, without modifying the wrapped class.

Builder:
MarginResultBuilder constructs MarginResult step-by-step with validation. The MarginResult constructor is package-private, so the builder is the only way to create one.

Singleton:
ReportRepository uses a static getInstance() method to ensure one shared in-memory store across the whole app.

Command:
CommandInvoker maintains a history stack of Command objects, enabling execute() and undo() on SaveReportCommand, DeleteReportCommand, and ExportReportCommand.

Template Method:
ReportGenerator defines a locked generate() skeleton (collect → calculate → format → output). ConsoleReportGenerator and CsvReportGenerator override only the steps that differ.

Factory Method:
ExpenseFactory is an interface. FixedExpenseFactory, VariableExpenseFactory, and RecurringExpenseFactory each implement it. Both ConsoleUI and ConsoleReportGenerator depend only on the interface via resolveFactory(), never calling concrete classes directly.

Developer Journey:

When developing this application, I initially had a 2D mindset. I had the basic calculation in mind and honestly what I was going for could have been done with a simple script. However utilizing the SDLC mindset I was able to sit down and think about what would make this the optimal helper. I would want to see an actual breakdown of what I inputed and also seeing actual percentages.

Now, we needed to implement 6 design patterns, how can I use these pattern to make my project more extensible and reusuable. I utilizied Calude and Chatgpt to outline particular use cases using my selected patters, and it gave me a great implementation plan.

Problems:

I ran into a issue where the command line prompts wasn't responding to the users inputs. It was only responding on 1 and 6. After some debugging I was able to modify the ConsoleUi file to remove a syntax error which allowed fucntionality to flow.

More power!!
Things I would implement to improve this application:
Report Generator PDF format:
This report Generator would be able to take the outputed format and give a more customized viewing.
Database storage:
I will also implement database storage for these genertated reports to be viewed at clients request.
Web app:
I would also host this application on a server for web app usage

HOW TO USE:

- Open Java Project in desired IDE
- Once project is opened, find Main.Java file
- Click the run button
- The terminal will open, giving you a list of options -
- To calculate your Margin, select 1/
- Follow each prompt until complete than enter done/
- Your fiamcial summary will populate in the terminal.
- Click save or no to save.
- You will be returned back to the main screen.
