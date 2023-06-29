Feature: Verify if Library add book api works fine

@AddBook @Regression
Scenario: Verify if addition of book works as expected
Given "baseURI" and "AddBook" Json payload
When user hits "AddBook" API using HTTP Method "POST"
Then Response Status code should be "200"
And "Msg" should be "successfully added"
And "ID" should be isbn+aisle

@DeleteBook @Regression
Scenario: Delete book by id
Given "baseURI" and "DeleteBook" Json payload
When user hits "DeleteBook" API using HTTP Method "DELETE"
Then Response Status code should be "200"
And "msg" should be "book is successfully deleted"

