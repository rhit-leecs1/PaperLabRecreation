import java.awt.*;
import java.util.*;
import javax.swing.*;
public class GeneButton extends JButton{
    private boolean bin;
    private Color color;
    private int num;
    public GeneButton(int num)
    {
    	super(""+ num);
    	bin = (int)Math.round(Math.random()) == 0 ? false : true;
    	if(!bin)
    	{
    		color = Color.black;
    		this.setBackground(color);
    		this.setForeground(Color.white);
    	}
    	if(bin)
    	{
    		color = Color.green;
    		this.setBackground(color);
    		this.setForeground(Color.black);
    	}
    	this.setBorderPainted(false);
    	this.setFocusPainted(false);
    	Dimension d = new Dimension(5,5);
    	this.setPreferredSize(d);
    	this.setMaximumSize(d);
    }
    public GeneButton(int num, Random r) {
    	super(""+ num);
    	bin = (int)Math.round(r.nextDouble()-.45) == 0 ? false : true;
    	if(!bin)
    	{
    		color = Color.black;
    		this.setBackground(color);
    		this.setForeground(Color.white);
    	}
    	if(bin)
    	{
    		color = Color.green;
    		this.setBackground(color);
    		this.setForeground(Color.black);
    	}
    	this.setBorderPainted(false);
    	this.setFocusPainted(false);
    	Dimension d = new Dimension(5,5);
    	this.setPreferredSize(d);
    	this.setMaximumSize(d);
	}
    public GeneButton(int num, boolean bin)
    {
    	super(""+ num);
    	this.bin = bin;
    	if(!bin)
    	{
    		color = Color.black;
    		this.setBackground(color);
    		this.setForeground(Color.white);
    	}
    	if(bin)
    	{
    		color = Color.green;
    		this.setBackground(color);
    		this.setForeground(Color.black);
    	}
    	this.setBorderPainted(false);
    	this.setFocusPainted(false);
    	Dimension d = new Dimension(5,5);
    	this.setPreferredSize(d);
    	this.setMaximumSize(d);
    }
	public String toString()
    {
    	return "Gene #" + num + ", " + bin;
    }
    public int getBin()
    {
    	return bin?1:0;
    }
    public Color getColor()
    {
    	return color;
    }
    public int getNum()
    {
    	return num;
    }
    public void flipBit()
    {
    	bin = !bin;
    	if(!bin)
    	{
    		color = Color.black;
    		this.setBackground(color);
    		this.setForeground(Color.white);
    	}
    	if(bin)
    	{
    		color = Color.green;
    		this.setBackground(color);
    		this.setForeground(Color.black);
    	}
    }
}
