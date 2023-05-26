package com.banking.details;

import java.util.Scanner;

public class BankAccount {
    
    private String accno;  
    private String name;  
    private String acc_type;  
    private long balance; 
    
    
    
    public void openAccount( Scanner sc) {  
        
        System.out.print("Enter Account No: ");  
        accno = sc.next();  
        System.out.print("Enter Account type: ");  
        acc_type = sc.next();  
        System.out.print("Enter Name: ");  
        name = sc.next();  
        System.out.print("Enter Balance: ");  
        balance = sc.nextLong(); 
 
    } 
    
    //method to deposit money  
    public void deposit(Scanner sc) {  
        long amt;  
        System.out.println("Enter the amount you want to deposit: ");  
        amt = sc.nextLong();  
        balance = balance + amt;  
       
    }  
    //method to withdraw money  
    public void withdrawal(Scanner sc) { 
        
        long amt;  
        System.out.println("Enter the amount you want to withdraw: ");  
        amt = sc.nextLong();  
        
        if (balance >= amt) {  
            balance = balance - amt;  
            System.out.println("Balance after withdrawal: " + balance);  
        } 
        else {  
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!" );  
        }  
    }  
    //method to search an account number  
    public boolean search(Scanner sc, String ac_no) {
        
        if (accno.equals(ac_no)) {  
            showAccount();  
            return (true);  
        }  
        return (false);  
    }  

    //method to display account details  
    public void showAccount() {  
        System.out.println("Name of account holder: " + name);  
        System.out.println("Account no.: " + accno);  
        System.out.println("Account type: " + acc_type);  
        System.out.println("Balance: " + balance);  
    }  
    
}
