package interview;

public class Journalist extends HomoSapiens {

    public String interview() {
        System.out.println("Как вам наша планета? Не желаете ли что-то купить?");
        System.out.println("Расскажите, пожалуйта, о себе!");
        System.out.println("Мы давно не видели такого храбреца!");
        return "Отлично! Как раз успеем к эфиру!";
    }

    public String scandalousInterview() {
        System.out.println("Нагло расталкивая окружающих," + getName() + "пробиралась к цели");
        System.out.println();
        return "Оно того стоило!";
    }

    public void photo() {
        ToPhoto toPhoto = new ToPhoto() {
            @Override
            public String takePhoto() {
                return "Фото готово!";
            }
        };
    }

    public void getAd() {
        System.out.println("Журналист всучила рекламный плакат");
        // traveler.hasAd = true
    }
    public String leave() {
        System.out.println("Спасайся кто может!");
        return "Журналист спешно покинул здание.";
    }
}
