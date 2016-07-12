/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mandelbrotset;

/**
 *
 * @author kumav1176
 */
public class ComplexNumber {

    double real;
    double imaginary;
        
    public ComplexNumber(double r, double i){
        real = r;
        imaginary = i;
    }
    public ComplexNumber(String n){
        int index;
        //get rid of spaces to make it uniform
        n = n.replaceAll(" ", "");
        
        //separate real from imaginary
        if(n.contains("+")){
            //real number might have + in front
            index = n.lastIndexOf("+");
            real = Double.parseDouble(n.substring(0, index));
            //include index to get  negative sign if needed
            imaginary = Double.parseDouble(n.substring(index, n.length()-1));
        }
        else if(n.contains("-")){
            if(n.contains("i")){
                //negative imaginary with no real component
                if(n.lastIndexOf("-") == 0){
                    real = 0;
                    imaginary = Double.parseDouble(n.substring(0, n.length()-1));
                }
                else{
                    //real number might have - in front
                    index = n.lastIndexOf("-");
                    real = Double.parseDouble(n.substring(0, index));
                    //include index to get  negative sign if needed
                    imaginary = Double.parseDouble(n.substring(index, n.length()-1));
                }
            }
            //if real with no imaginary component
            else{
                real = Double.parseDouble(n);
                imaginary = 0;
            }
        }
        else{
            //if imaginary with no real component
            if(n.contains("i")){
                real = 0;
                imaginary = Double.parseDouble(n.substring(0, n.length()-1));
            }
            else{
                real = Double.parseDouble(n);
                imaginary = 0;
            }
        }
    }
    public void displayComplexNumber(){
        String realString = "";
        String imaginaryString = "";
        //add imaginary number
        if(imaginary > 0){
            if(imaginary == 1){
                imaginaryString = " + i";
            }
            else{
                imaginaryString = " + " + imaginary + "i";
            }
        }
        //subtract imaginary number
        else if(imaginary < 0){
            if(imaginary == -1){
                imaginaryString = " - i";
            }
            else{
                imaginaryString = " - " + -imaginary + "i";
            }
        }
        
        if(real != 0){
            realString = Double.toString(real);
        }
        else{
            imaginaryString = imaginary + "i";
        }
        System.out.println(realString + imaginaryString);
    }
    
    public ComplexNumber add( ComplexNumber other){
        double realSum = this.real + other.real;
        double imaginarySum = this.imaginary + other.imaginary;
        
        ComplexNumber sum = new ComplexNumber(realSum, imaginarySum);
        return sum;
    }
    
    public ComplexNumber conjugate(ComplexNumber d){
        ComplexNumber conjugate = new ComplexNumber(d.real, -d.imaginary);
        return conjugate;
    }
    public double absValue(){
        double absValue = Math.pow(this.real, 2) + Math.pow(this.imaginary,2);
        return absValue;
    }
    public ComplexNumber multiByScalar(double s){
        ComplexNumber product = new ComplexNumber(this.real*s,this.imaginary*s);
        return product;
    }
    public ComplexNumber multiByCN(ComplexNumber other){
        ComplexNumber product = new ComplexNumber(0,0);
        //foil
        product.real = this.real * other.real - this.imaginary * other.imaginary;
        product.imaginary = this.real * other.imaginary + 
                this.imaginary * other.real;
        return product;
    }
    public ComplexNumber divideBy(ComplexNumber other){
        //denominator is absolute value 
        double denominator = other.absValue();
        //numerator is multiplied by the conjugate
        ComplexNumber numerator = this.multiByCN(conjugate(other));
        //simplify fraction
        ComplexNumber quotient = new ComplexNumber(numerator.real/denominator,
                numerator.imaginary/denominator);
        return quotient;
    }
}
