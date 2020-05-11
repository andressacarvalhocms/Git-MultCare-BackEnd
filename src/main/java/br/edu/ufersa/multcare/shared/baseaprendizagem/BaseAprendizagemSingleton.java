package br.edu.ufersa.multcare.shared.baseaprendizagem;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class BaseAprendizagemSingleton {

    private static DataSource dataSource;

    private BaseAprendizagemSingleton() {
    }

    public static synchronized Instances obterBaseAprendizagem() throws Exception {

        if(dataSource == null) {
            dataSource = new DataSource("base_treinamento.arff");
        }
        return dataSource.getDataSet();
    }
}
