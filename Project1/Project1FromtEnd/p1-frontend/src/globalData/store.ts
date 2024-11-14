/* this is a very basic implementation of a store, which is global data storage

any data that you want to use throuhhout the entire app can reside here
*/

export const store:any = {
    //Let's store loggedInUser info (filled after successful login)
    loggedInUser:{
        userId:0,
        firstName:"",
        lastName:"",
        username:"",
        role:''
    }, // we could have modeled this after a UserInterface, but i didnt 

    //think about your requirements when it comes to storeing global data
    //you only need to globally store data you intend to use in multiple components

    //for instance, you could store the base URL to the server

    baseUrl:"http://localhost:7005/",

    reimbursementObj:{
        reimId:0,
        description:"",
        amount:"",
        status:""
    }
}