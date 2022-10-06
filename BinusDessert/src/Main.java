import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	Scanner sc = new Scanner(System.in);
	Connect con = Connect.getConnection();

	ArrayList <Dessert> desserts = new ArrayList<>();
	ArrayList <User> users = new ArrayList<>();
	ArrayList <Transaction> transactions = new ArrayList<>();

	public void init(){ //inisialisasi data dari database masuk ke arraylist
		//Preparedstatement -> lebih mudah
		//executeQuery() / executeUpdate()
		// executeQuery() -> SELECT
		// executeUpdate() -> INSERT,UPDATE,DELETE

		//Dessert - Icecream
		String query = "SELECT * FROM icecreams";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			//Variabel Temp Buat Icecream
			int id,price;
			String name,type,creamName;
			ResultSet rs = ps.executeQuery();
			while(rs.next()){ //-->mirip seperti pointer
				id = rs.getInt("DessertId");
				name = rs.getString("DessertName");
				type = rs.getString("DessertType");
				price = rs.getInt("DessertPrice");
				creamName = rs.getString("CreamName");
				desserts.add(new Icecream(id,name,type,price,creamName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		// Dessert - Yoghurts
		query = "SELECT * FROM yoghurts";
		ps = con.prepareStatement(query);
		try {
			//Variabel Temp Buat Yoghurts
			int id,price;
			String name,type,probioticName;
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				id = rs.getInt("DessertId");
				name = rs.getString("DessertName");
				type = rs.getString("DessertType");
				price = rs.getInt("DessertPrice");
				probioticName = rs.getString("ProbioticName");
				desserts.add(new Icecream(id,name,type,price,probioticName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		// User
		query = "SELECT * FROM users";
		ps = con.prepareStatement(query);
		try {
			//Variabel Temp Buat User
			int id,age;
			String name,email;
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				id = rs.getInt("UserId");
				name = rs.getString("UserName");
				email = rs.getString("UserEmail");
				age = rs.getInt("UserAge");
				users.add(new User(id,name,email,age));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		//Transaction - icecream
		query = "SELECT TransactionId,UserName,DessertName,DessertPrice,Quantity "+
		"FROM transactions t "+
		"JOIN users u ON u.UserId = t.UserId "+
		"JOIN icecreams i ON i.DessertId = t.DessertId ";
		ps = con.prepareStatement(query);
		try {
			String id, userName, dessertName;
			int price, quantity;
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				id = rs.getString("TransactionId");
				userName = rs.getString("UserName");
				dessertName = rs.getString("DessertName");
				price = rs.getInt("DessertPrice");
				quantity = rs.getInt("Quantity");
				transactions.add(new Transaction(id,userName,dessertName,price,quantity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Transaction - yoghurt
		query = "SELECT TransactionId,UserName,DessertName,DessertPrice,Quantity "+
		"FROM transactions t "+
		"JOIN users u ON u.UserId = t.UserId "+
		"JOIN yoghurts i ON i.DessertId = t.DessertId ";
		ps = con.prepareStatement(query);
		try {
			String id, userName, dessertName;
			int price, quantity;
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				id = rs.getString("TransactionId");
				userName = rs.getString("UserName");
				dessertName = rs.getString("DessertName");
				price = rs.getInt("DessertPrice");
				quantity = rs.getInt("Quantity");
				transactions.add(new Transaction(id,userName,dessertName,price,quantity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printAll(){
		System.out.println("Dessert");
		for(Dessert d : desserts){
			d.print();
		}

		System.out.println("User");
		for(User u : users){
			u.print();
		}

		System.out.println("Transaction");
		for(Transaction t : transactions){
			t.print();
		}
	}

	public Main(){
		init();
		// printAll();
		while(true){
			System.out.println("Binus Desset Check");
			System.out.println("================");
			System.out.println("1. View All Transactions");
			System.out.println("2. Buy Dessert");
			System.out.println("3. Delete Transaction");
			System.out.println("4. Exit");
			int choice;
			while(true){
				System.out.print(">> ");
				try {
					choice = sc.nextInt();
					sc.nextLine();
					if(choice >= 1 && choice <= 4){
						break;
					}
				} catch (Exception e) {
					//TODO: handle exception
				}
			}
			if(choice == 1){
				viewAllTransactions();
			} else if(choice == 2){
				buyDessert();
			} else if(choice == 3){
				deleteTransaction();
			} else if(choice == 4){
				System.out.println("Thank you for using Binus Dessert Checker!");
				return;
			}
		}
	}

	private void deleteTransaction() {
		if(transactions.isEmpty()){
			System.out.println("No Transaction Available");
			return;
		}
		else{
			System.out.println("List of Transactions");
			System.out.println("====================");
			for(Transaction t : transactions){
				t.print();
			}
			int choice=0;
			while(true){
				System.out.print("Choose number you want to delete[1-" + transactions.size() +"]:");
				try {
					choice = sc.nextInt();
					sc.nextLine();
					if(choice >= 1 && choice <= transactions.size()){
						break;
					}
				} catch (Exception e) {
				}
			}
			String transactionId = transactions.get(choice-1).getId();
			//delete didatabse
			String query = "DELETE FROM transactions WHERE TransactionId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			try {
				ps.setString(1, transactionId);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//delete di arrayList
			transactions.remove(choice-1);
			System.out.println("Transaction "+ transactionId + " deleted");
		}
	}

	private void buyDessert() {
		String name,email;
		int age;
		
		//name
		while(true){
			System.out.println("Name [more than 5 char]:");
			name = sc.nextLine();
			if(name.length() > 5){
				break;
			}
		}

		//email
		while(true){
			System.out.println("Email [contains '@' and ends with '.com']: ");
			email = sc.nextLine();
			if(email.contains("@") && email.endsWith(".com")){
				break;
			}
		}

		//age
		while(true){
			System.out.println("Age [greater than 0]: ");
			try {
				age = sc.nextInt();
				sc.nextLine();
				if(age > 0){
					break;
				}
			} catch (Exception e) {
			
			}
		}

		//Looping untuk user agar dapat mengetahui apakah user sudah ada didatabase
		Boolean isFound = false;
		int userId = 0;
		for(User u : users){
			if(u.getEmail().equals(email)){
				userId = u.getId();
				isFound = true;
				break;
			}
		}

		if(isFound){
			System.out.println("User already exists");
		} else {
			System.out.println("User not found");
			//Ambil Id tertinggi dari user
			userId = 0;
			for(User u : users){
				if(u.getId() > userId){
					userId = u.getId();
				}
			}
			userId++;
			//Tambahkan user baru ke database dan arraylist
			String query = "INSERT INTO users VALUES(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			try {
				ps.setInt(1, userId);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setInt(4, age);
				ps.executeUpdate();
				//tambah ke arraylist
				users.add(new User(userId, name, email, age));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//Display all dessert
		System.out.println("Dessert List");
		for(Dessert d : desserts){
			d.print();
		}

		//Pilih dessert yang akan dibeli
		int dessertId=0;
		int choice=0;
		while(true){
			System.out.println("Choose Dessert [between 1 and 10]: ");
			try {
				choice = sc.nextInt();
				sc.nextLine();
				if(choice >= 1 && choice <= 10){
					dessertId = desserts.get(choice-1).getId();
					break;
				}
			} catch (Exception e) {
				//TODO: handle exception
			}
		}

		//Pili quantity dari dessert
		int quantity=0;
		while(true){
			System.out.println("Choose Quantity [greater than 0]: ");
			try {
				quantity = sc.nextInt();
				sc.nextLine();
				if(quantity >= 1){
					break;
					
				}
			} catch (Exception e) {
				//TODO: handle exception
			}
		}

		//display detail transaciton
		Dessert temp = desserts.get(choice-1);
		System.out.println("Detail Transaction");
		System.out.println("Name: " + temp.getName());
		System.out.println("Price: " + temp.getPrice());
		System.out.println("Quantity: " + quantity);
		System.out.println("Total Price: " + (temp.getPrice() * quantity));

		//Cari id tertinggi dari transaction
		int maxId=0;
		for(Transaction t : transactions){
			String tempId1 = t.getId().substring(2,5); //001
			int tempId2 = Integer.parseInt(tempId1); //1
			if( tempId2 > maxId){
				maxId = tempId2;
			}
		}
		maxId++;
		String transactionId = String.format("TR%03d", maxId);

		//Insert ke databse
		String query = "INSERT INTO transactions VALUES(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		try{
			ps.setString(1, transactionId);
			ps.setInt(2, userId);
			ps.setInt(3, dessertId);
			ps.setInt(4, quantity);
			ps.executeUpdate();
			//Insert juga ke arraylist
			transactions.add(new Transaction(transactionId, name, desserts.get(choice-1).getName(), quantity , desserts.get(choice-1).getPrice()));
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("Successfully insert!");
	}

	private void viewAllTransactions() {
		if(transactions.isEmpty()){
			System.out.println("No Transaction Available");
			return;
		}
		else{
			for(Transaction t : transactions){
				t.print();
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
