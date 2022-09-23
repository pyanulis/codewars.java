public class Vehicle {
    protected int m_speed;

    public void speedUp(int increment){
        m_speed += increment;
    }

    public void speedDown(int decrement){
        m_speed -= decrement;
    }
}
