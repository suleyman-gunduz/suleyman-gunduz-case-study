# case-study
- I put the apidemo.json file in the raw folder and read the data from here. It first reads the JSON file and performs data synchronization and then fetches data from sqlite.
- If the sqlite database is empty when data comes from JSON, all the data is saved in the sqlite database.
- I used the pagination structure in the sqlite database. In other words, as the number of data increases, the program will not experience any negative situation. I set the page size to 20. It can be changed in the Constants class if desired.
- Users can search by label on the list. The list is sorted according to the rating by default. Users can change the sorting criteria if they wish.
- When user taps on the country part on the list the relevant location is opened on Google Maps.
- When the statistics button is touched, the page with statistical data and graphics opens.
I used MVP as the project architecture. I used third party libraries such as Dagger 2, Retrofit (for mapping), Room, Butterknife, MPAndroidChart.
I hope you will like it.

