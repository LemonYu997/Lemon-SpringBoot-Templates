package cn.lemonyyy.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class A {
    private Double rmssd;

    public A(Function<double[], Double> calculateRmssdMethod) {
        rmssd = rmssd * 2;

        this.rmssd = calculateRmssdMethod.apply(new double[]{});
    }
}
