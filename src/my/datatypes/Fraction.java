package my.datatypes;
/**
 * @author David
 *
 */
public class Fraction {
	protected int numerator;
	protected int denominator;
	protected final int e = 10000;
	
	public int Numerator() {
		return numerator;
	}
	
	public int Denominator() {
		return denominator;
	}
	
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public Fraction(int numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}
	
	public Fraction(float numerator) {
		Fraction tmp = SimplifyFraction(new Fraction((int)(numerator * e), e));
		this.denominator = tmp.denominator;
		this.numerator = tmp.numerator;
	}
	
	public Fraction(Fraction term) {
		this.numerator = term.numerator;
		this.denominator = term.denominator;
	}
	
	public static int EuclidGCD(int first, int second) {
		while(second != 0) {
			int r = first % second;
			first = second;
			second = r;
		}
		return first;
	}
	
	public static Fraction AddFractions(Fraction first, Fraction second) {
		int numerator, denominator;
		denominator = findCommonDenominator(first, second);
		numerator = first.numerator * denominator/first.denominator + second.numerator * denominator/second.denominator;
		return SimplifyFraction(new Fraction(numerator, denominator));
	}
	
	public static Fraction AddFractions(Fraction first, int num) {
		Fraction second = new Fraction(num);
		return AddFractions(first, second);
	}
	
	public Fraction addFraction(Fraction term) {
		int numerator, denominator;
		denominator = findCommonDenominator(this, term);
		numerator = this.numerator * denominator/this.denominator + term.numerator * denominator/term.denominator;
		Fraction tmp = SimplifyFraction(new Fraction(numerator, denominator));
		this.numerator = tmp.numerator;
		this.denominator = tmp.denominator;
		return this;
	}
	
	public Fraction addFraction(int num) {
		Fraction term = new Fraction(num);
		return addFraction(term);
	}
	
	public static Fraction SubstractFractions(Fraction first, Fraction second) {
		int numerator, denominator;
		denominator = findCommonDenominator(first, second);
		numerator = first.numerator * denominator/first.denominator - second.numerator * denominator/second.denominator;
		return SimplifyFraction(new Fraction(numerator, denominator));
	}
	
	public static Fraction SubstractFractions(Fraction first, int num) {
		Fraction second = new Fraction(num);
		return SubstractFractions(first, second);
	}
	
	public Fraction subtractFraction(Fraction term) {
		int numerator, denominator;
		denominator = findCommonDenominator(this, term);
		numerator = this.numerator * denominator/this.denominator - term.numerator * denominator/term.denominator;
		Fraction tmp = SimplifyFraction(new Fraction(numerator, denominator));
		this.numerator = tmp.numerator;
		this.denominator = tmp.denominator;
		return this;
	}
	
	public Fraction subtractFraction(int num) {
		Fraction term = new Fraction(num); 
		return subtractFraction(term);
	}
	
	public static Fraction MultiplyFractions(Fraction first, Fraction second) {
		int numerator, denominator;
		numerator = first.numerator * second.numerator;
		denominator = first.denominator * second.denominator;
		return SimplifyFraction(new Fraction(numerator, denominator));
	}
	
	public Fraction multiplyFraction(Fraction term) {
		this.numerator = this.numerator * term.numerator;
		this.denominator = this.denominator * term.denominator;
		Fraction tmp = SimplifyFraction(this);
		this.numerator = tmp.numerator;
		this.denominator = tmp.denominator;
		return this;
	}
	
	public Fraction multiplyFraction(int num) {
		Fraction term = new Fraction(num);
		return multiplyFraction(term);
	}
	
	public static Fraction DivideFractions(Fraction first, Fraction second) {
		second = new Fraction(second.denominator, second.numerator);
		return MultiplyFractions(first, second);
	}
	
	public Fraction divideFraction(Fraction term) {
		term = new Fraction(term.denominator, term.numerator);
		return this.multiplyFraction(term);
	}
	
	public Fraction divideFraction(int num) {
		Fraction term = new Fraction(num);
		return this.divideFraction(term);
	}
	
	public static boolean FractionsCompare(Fraction first, Fraction second) {
		if(first.numerator == second.numerator && first.denominator == second.denominator) {
			return true;
		}
		else return false;
	}
	
	public boolean fractionCompare(Fraction term) {
		if(this.numerator == term.numerator && this.denominator == term.denominator) {
			return true;
		}
		else return false;
	}
	
	public boolean fractionCompare(int num) {
		Fraction term = new Fraction(num);
		return fractionCompare(term);
	}
	
	public static boolean GreaterEquals(Fraction first, Fraction second) {
		int commonDenominator = findCommonDenominator(first, second);
		int a = commonDenominator / first.denominator * first.numerator;
		int b  = commonDenominator / second.denominator * second.numerator;
		return (a >= b)?(true):(false);
		
	}
	
	public boolean greaterEquals(Fraction term) {
		return GreaterEquals(this, term);
	}
	
	public static boolean Less(Fraction first, Fraction second) {
		return !GreaterEquals(first, second);
	}
	
	public boolean less(Fraction term) {
		return !greaterEquals(term);
	}
	
	public static Fraction[][] IntegerMatrixTranslate(int[][] matrix) {
		Fraction[][] result = new Fraction[matrix.length][];
		for(int i = 0; i < result.length; i++) {
			result[i] = new Fraction[matrix[i].length];
			for(int j = 0; j < result[i].length; j++) {
				result[i][j] = new Fraction(matrix[i][j]);
			}
		}
		return result;
	}
	
	public static Fraction[] IntegerArrayTranslate(int[] array) {
		Fraction[] result = new Fraction[array.length];
		for(int i = 0; i < result.length; i++) {
			result[i] = new Fraction(array[i]);
		}
		return result;
	}
	
	public boolean isPositive() {
		if(this.numerator > 0 && this.denominator > 0) {
			return true;
		}
		else return false;
	}
	
	/**
	 * @param
	 * @param second
	 * @return a correct denominator, but not the simplest.
	 */
	protected static int findCommonDenominator(Fraction first, Fraction second) {
		int denominator;
		if(first.denominator != second.denominator) {
			denominator = first.denominator * second.denominator;	
		}
		else denominator = first.denominator;
		return denominator;
	}
	
	public static Fraction SimplifyFraction(Fraction main) {
		int simplifyer = EuclidGCD(main.numerator, main.denominator);
		int denominator = main.denominator / simplifyer;
		int numerator = main.numerator / simplifyer;
		if(numerator < 0 && denominator < 0 || numerator > 0 && denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}
		
		return new Fraction(numerator, denominator);
	}
}
