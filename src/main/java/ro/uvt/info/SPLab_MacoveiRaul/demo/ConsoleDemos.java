package ro.uvt.info.SPLab_MacoveiRaul.demo;

import ro.uvt.info.SPLab_MacoveiRaul.model.AlignCenter;
import ro.uvt.info.SPLab_MacoveiRaul.model.AlignLeft;
import ro.uvt.info.SPLab_MacoveiRaul.model.AlignRight;
import ro.uvt.info.SPLab_MacoveiRaul.model.Author;
import ro.uvt.info.SPLab_MacoveiRaul.model.Book;
import ro.uvt.info.SPLab_MacoveiRaul.model.BookStatistics;
import ro.uvt.info.SPLab_MacoveiRaul.model.GenerateToC;
import ro.uvt.info.SPLab_MacoveiRaul.model.Image;
import ro.uvt.info.SPLab_MacoveiRaul.model.ImageProxy;
import ro.uvt.info.SPLab_MacoveiRaul.model.Paragraph;
import ro.uvt.info.SPLab_MacoveiRaul.model.RenderContentVisitor;
import ro.uvt.info.SPLab_MacoveiRaul.model.Section;
import ro.uvt.info.SPLab_MacoveiRaul.model.Table;

public class ConsoleDemos {

	// demo-urile din laboratoarele 1-3; nu sunt apelate la pornirea aplicatiei web
	public static void runAll() {
		lab1CompositeDemo();
		System.out.println();
		lab1SharingDemo();
		System.out.println();
		lab2StrategyDemo();
		System.out.println();
		lab2ProxyDemo();
		System.out.println();
		lab3StatisticsDemo();
		System.out.println();
		lab3ToCDemo();
	}

	public static void lab1CompositeDemo() {
		Book noapteBuna = new Book("Noapte buna, copii!");
		Author rpGheo = new Author("Radu Pavel", "Gheo");
		noapteBuna.addAuthor(rpGheo);

		Section cap1 = new Section("Capitolul 1");
		Section cap11 = new Section("Capitolul 1.1");
		Section cap111 = new Section("Capitolul 1.1.1");
		Section cap1111 = new Section("Subchapter 1.1.1.1");

		noapteBuna.addContent(new Paragraph("Multumesc celor care ..."));
		noapteBuna.addContent(cap1);

		cap1.add(new Paragraph("Moto capitol"));
		cap1.add(cap11);

		cap11.add(new Paragraph("Text from subchapter 1.1"));
		cap11.add(cap111);

		cap111.add(new Paragraph("Text from subchapter 1.1.1"));
		cap111.add(cap1111);

		cap1111.add(new Image("Image subchapter 1.1.1.1"));

		noapteBuna.accept(new RenderContentVisitor());
	}

	public static void lab1SharingDemo() {
		Section cap1 = new Section("Capitolul 1");
		Section cap2 = new Section("Capitolul 2");
		Paragraph p = new Paragraph("Paragraf unic");

		cap1.add(p);
		System.out.println("1. Added " + p + " to " + cap1 + " -> parent: " + p.getParent());

		try {
			cap2.add(p);
		} catch (IllegalStateException e) {
			System.out.println("2. Adding it to " + cap2 + " failed: " + e.getMessage());
		}

		cap1.remove(p);
		System.out.println("3. Removed " + p + " from " + cap1 + " -> parent: " + p.getParent());
		cap2.add(p);
		System.out.println("   Added " + p + " to " + cap2 + " -> parent: " + p.getParent());

		cap1.accept(new RenderContentVisitor());
		cap2.accept(new RenderContentVisitor());
	}

	public static void lab2StrategyDemo() {
		Section cap1 = new Section("Capitolul 1");
		Paragraph p1 = new Paragraph("Paragraph 1");
		cap1.add(p1);
		Paragraph p2 = new Paragraph("Paragraph 2");
		cap1.add(p2);
		Paragraph p3 = new Paragraph("Paragraph 3");
		cap1.add(p3);
		Paragraph p4 = new Paragraph("Paragraph 4");
		cap1.add(p4);

		System.out.println("Printing without Alignment");
		System.out.println();
		cap1.accept(new RenderContentVisitor());

		p1.setAlignStrategy(new AlignCenter());
		p2.setAlignStrategy(new AlignRight());
		p3.setAlignStrategy(new AlignLeft());

		System.out.println();
		System.out.println("Printing with Alignment");
		System.out.println();
		cap1.accept(new RenderContentVisitor());
	}

	public static void lab2ProxyDemo() {
		long startTime = System.currentTimeMillis();
		ImageProxy img1 = new ImageProxy("Pamela Anderson");
		ImageProxy img2 = new ImageProxy("Kim Kardashian");
		ImageProxy img3 = new ImageProxy("Kirby Griffin");
		Section playboyS1 = new Section("Front Cover");
		playboyS1.add(img1);
		Section playboyS2 = new Section("Summer Girls");
		playboyS2.add(img2);
		playboyS2.add(img3);
		Book playboy = new Book("Playboy");
		playboy.addContent(playboyS1);
		playboy.addContent(playboyS2);
		long endTime = System.currentTimeMillis();
		System.out.println("Creation of the content took " + (endTime - startTime) + " milliseconds");

		startTime = System.currentTimeMillis();
		playboyS1.accept(new RenderContentVisitor());
		endTime = System.currentTimeMillis();
		System.out.println("Printing of the section 1 took " + (endTime - startTime) + " milliseconds");

		startTime = System.currentTimeMillis();
		playboyS1.accept(new RenderContentVisitor());
		endTime = System.currentTimeMillis();
		System.out.println("Printing again the section 1 took " + (endTime - startTime) + " milliseconds");
	}

	public static void lab3StatisticsDemo() {
		Section cap1 = new Section("Capitolul 1");
		Paragraph p1 = new Paragraph("Paragraph 1");
		cap1.add(p1);
		Paragraph p2 = new Paragraph("Paragraph 2");
		cap1.add(p2);
		Paragraph p3 = new Paragraph("Paragraph 3");
		cap1.add(p3);
		Paragraph p4 = new Paragraph("Paragraph 4");
		cap1.add(p4);
		cap1.add(new ImageProxy("ImageOne"));
		cap1.add(new Image("ImageTwo"));
		cap1.add(new Paragraph("Some text"));
		cap1.add(new Table("Table 1"));

		BookStatistics stats = new BookStatistics();
		cap1.accept(stats);
		stats.printStatistics();
	}

	public static void lab3ToCDemo() {
		Book carte = new Book("Design Patterns");
		Section cap1 = new Section("Capitolul 1");
		Section cap11 = new Section("Capitolul 1.1");
		Section cap111 = new Section("Capitolul 1.1.1");
		Section cap12 = new Section("Capitolul 1.2");
		Section cap2 = new Section("Capitolul 2");
		Section cap21 = new Section("Capitolul 2.1");

		carte.addContent(new Paragraph("Prefata"));
		carte.addContent(cap1);
		carte.addContent(cap2);

		cap1.add(new Paragraph("Introducere"));
		cap1.add(cap11);
		cap1.add(cap12);
		cap11.add(cap111);
		cap111.add(new Table("Tabel 1"));
		cap12.add(new Paragraph("Text din 1.2"));
		cap2.add(cap21);

		GenerateToC generator = new GenerateToC();
		carte.accept(generator);
		generator.getToC().render();
	}

}
