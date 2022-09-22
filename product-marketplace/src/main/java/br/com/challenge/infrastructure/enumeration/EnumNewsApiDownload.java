package br.com.challenge.infrastructure.enumeration;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public enum EnumNewsApiDownload {

	PRIMEIRA_CARGA(LocalDateTime.now().with(LocalTime.MIDNIGHT),
			LocalDateTime.now().withHour(6).withMinute(0).withSecond(0)),

	SEGUNDA_CARGA(LocalDateTime.now().withHour(6).withMinute(0).withSecond(1),
			LocalDateTime.now().withHour(12).withMinute(0).withSecond(0)),

	TERCEIRA_CARGA(LocalDateTime.now().withHour(12).withMinute(0).withSecond(1),
			LocalDateTime.now().withHour(18).withMinute(0).withSecond(0)),

	QUARTA_CARGA(LocalDateTime.now().withHour(18).withMinute(0).withSecond(1),
			LocalDateTime.now().withHour(23).withMinute(59).withSecond(59));

	public LocalDateTime inicio;

	public LocalDateTime fim;

	EnumNewsApiDownload(LocalDateTime inicio, LocalDateTime fim) {
		this.inicio = inicio;
		this.fim = fim;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public static EnumNewsApiDownload getPeriod() {
		LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

		return Arrays.asList(EnumNewsApiDownload.values()).stream()
				.filter(item -> EnumNewsApiDownload.checkBetween(now, item)).findFirst().orElse(null);
	}

	private static boolean checkBetween(LocalDateTime dateToCheck, EnumNewsApiDownload time) {
		return dateToCheck.compareTo(time.inicio.truncatedTo(ChronoUnit.SECONDS)) >= 0
				&& dateToCheck.compareTo(time.fim.truncatedTo(ChronoUnit.SECONDS)) <= 0;
	}
}
