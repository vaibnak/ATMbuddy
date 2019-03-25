# Description
People face a lot of problems regarding ATMs, for which they generally cant do anything.<br>
This app aims to solve some of those problems, It has some solutions to the problems which will make using the 
ATM for common public an easy and smooth process.

# Overview of the App
This App has been built keeping in mind 2 types of users
1) Banks
2) Customers

### Customers ###
* Customers will register into the App once and giving their Name,Contact number and Address.
* Their location coordinates are obtained at that time using `FusedLocationProvider` API of Google
* Customers are then shown the ATMs in their city, with their status as functional or non-funcional, the bank to which they belong
  and also the distance b/w their location and ATM location
* Customers can click the ATM which they want and it will open a google Maps activity telling in navigation mode, telling them
  the path from their location to ATM.
* If the Customer wants to notify the bank about the malfunctioning of the ATM machines, or any another issue then can do so
  long clicking the ATM and submitting his complaint.
* Customer will be logged in for future, Untill he himself logs out by going to the logout button.

### Banks ###
* Banks will have to register giving the Bank name, Bank code(Uniqe code which identifies the Bank) and address of the Bank.
* Banks will see all their existing ATMs and their status will be denoted by the color of the `cardview`
  * red - for non-functional ATMs
  * green - for functional ATMs
* Banks can read about the complaints of the users of ATMs by going to the review section, where all complaints will be shown.
* Banks can add ATMs by clicking onthe `floatingActionBar` and filling form, which asks for
  * Code - This is used for uniquely identifying ATMs by Banks
  * Adress - This is address is converted to Location coordinates using `Geocoding`.
  * Status - This is set as working or not working based on the particular ATM.
* On getting a review which notifies the bank about the ATM machine not working, or any other reasons for not working of ATMs
  bank can long click on the ATM cardview to change its status from working to non-working.

### Some Screenshots from the working module ###
<p align="center">
  <img src="https://github.com/vaibnak/ATMbuddy/blob/master/homeScreen.png" width="200" height="300" />
  <img src="https://github.com/vaibnak/ATMbuddy/blob/master/customerReg.png" width="200" height="300"/>
  <img src="https://github.com/vaibnak/ATMbuddy/blob/master/customerBanks.png" width="200" height="300"/>
  <img src="https://github.com/vaibnak/ATMbuddy/blob/master/ActivityonClicking.png" width="200" height="300"/>
  </p>  
<p align="center">
  <img src="https://github.com/vaibnak/ATMbuddy/blob/master/complaintPage.png" width="200" height="300"/>
  <img src="https://github.com/vaibnak/ATMbuddy/blob/master/bankAtmadding.png" width="200" height="300"/>
  <img src="https://github.com/vaibnak/ATMbuddy/blob/master/bankatm2.png" width="200" height="300"/>
  <img src="https://github.com/vaibnak/ATMbuddy/blob/master/reviewAtm.png" width="200" height="300"/>
<p>

# Technologies Used
  Android Studio, Java, xml, Firebase(for all backend work).
# Note
  This repository is open to further improvements, please raise an issue if you find one or give a suggestion if you have one,
  also star it if you liked the concept.
  




