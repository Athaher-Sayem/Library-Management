<h1 align="center" style="color: #4CAF50;">📚 Library Management System</h1>

<p align="center">
  <b>A simple console-based Java application</b> to manage books, issue & return — perfect for small libraries or learning project.<br>
  Built with ❤️ in Java | Eclipse Project
</p>

<hr style="border: 1px solid #4CAF50;">

<h2 style="color: #2196F3;">✨ Features</h2>

<ul>
  <li>Add new books (ID, Title, Author)</li>
  <li>View all books with status (Available / Issued)</li>
  <li>Issue book to student</li>
  <li>Return book back to library</li>
  <li>Easy menu-driven console interface</li>
</ul>

<h2 style="color: #2196F3;">🛠️ Technologies</h2>

<table>
  <tr>
    <th>Category</th>
    <th>Details</th>
  </tr>
  <tr>
    <td>Language</td>
    <td>Java</td>
  </tr>
  <tr>
    <td>IDE</td>
    <td>Eclipse</td>
  </tr>
  <tr>
    <td>Storage</td>
    <td>In-memory (ArrayList)</td>
  </tr>
  <tr>
    <td>Interface</td>
    <td>Console (Scanner)</td>
  </tr>
</table>

<h2 style="color: #2196F3;">🚀 How to Run</h2>

<ol>
  <li>Clone the repo:<br>
    <code>git clone https://github.com/Athaher-Sayem/Library-Management.git</code>
  </li>
  <li>Open in Eclipse:<br>
    File → Import → Existing Projects into Workspace → Select folder
  </li>
  <li>Run:<br>
    Right-click <code>Main.java</code> → Run As → Java Application
  </li>
  <li>Use menu:
    <pre>
=== Library Management System ===
1. Add Book
2. View All Books
3. Issue Book
4. Return Book
5. Exit
Enter choice: 
    </pre>
  </li>
</ol>

<h2 style="color: #2196F3;">📝 Code Snippets</h2>

<h3>Main Menu (Main.java)</h3>

```java
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // Library / Book list object here...

    while (true) {
        System.out.println("\n=== Library Management System ===");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine(); // clear buffer

        switch (choice) {
            case 1: addBook(sc); break;
            case 2: viewBooks(); break;
            case 3: issueBook(sc); break;
            case 4: returnBook(sc); break;
            case 5:
                System.out.println("Thank you! Visit again.");
                sc.close();
                return;
            default:
                System.out.println("Invalid choice, try again.");
        }
    }
}
