import cv2
from deepface import DeepFace
import os
import time
from pathlib import Path
import numpy as np

class ImageFaceAnalyzer:
    def __init__(self, input_folder="images", output_folder="results"):
        """
        Инициализация анализатора изображений
        """
        self.input_folder = input_folder
        self.output_folder = output_folder
        self.processed_count = 0
        self.failed_count = 0
        
        if not os.path.exists(self.output_folder):
            os.makedirs(self.output_folder)

    def get_image_files(self):
        """
        Получаем список изображений для обработки
        """
        valid_extensions = {'.jpg', '.jpeg', '.png'}
        image_files = []
        
        for file in os.listdir(self.input_folder):
            file_path = os.path.join(self.input_folder, file)
            if os.path.isfile(file_path) and Path(file).suffix.lower() in valid_extensions:
                image_files.append(file_path)
                
        return sorted(image_files)
    
    def analyze_image(self, image_path):
        """
        Анализ изображения на наличие лиц
        """
        try:
            frame = cv2.imread(image_path)
            if frame is None:
                print(f"Не удалось загрузить изображение: {image_path}")
                return None, None
            
            analysis = DeepFace.analyze(
                img_path=frame,
                actions=['age', 'gender', 'emotion'],
                enforce_detection=False,
                detector_backend='opencv',
                silent=True
            )
            
            if isinstance(analysis, list) and len(analysis) > 0:
                return frame, analysis[0]
            else:
                return frame, None
                
        except Exception as e:
            print(f"Ошибка при анализе {image_path}: {e}")
            return None, None
    
    def draw_results(self, frame, results):
        """
        Отрисовка результатов на изображении с проверкой границ
        """
        if not results or 'region' not in results:
            return frame
            
        x = results['region']['x']
        y = results['region']['y']
        w = results['region']['w']
        h = results['region']['h']
        
        cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 0, 255), 2)
        
        age = results.get('age', 'N/A')
        gender = results.get('dominant_gender', 'N/A')
        emotion = results.get('dominant_emotion', 'N/A')
        
        info_lines = [
            f"Age: {age}",
            f"Gender: {gender}",
            f"Emotion: {emotion}"
        ]
        
        line_height = 25
        total_text_height = len(info_lines) * line_height
        text_x = x
        
        if y + h + total_text_height + 10 < frame.shape[0]:
            text_start_y = y + h + 20
            for i, line in enumerate(info_lines):
                y_pos = text_start_y + i * line_height
                cv2.putText(frame, line, (text_x, y_pos),
                           cv2.FONT_HERSHEY_DUPLEX, 0.7, (0, 0, 255), 2)
        else:
            text_start_y = y - 10
            for i, line in enumerate(info_lines):
                y_pos = text_start_y - i * line_height
                if y_pos > 20:
                    cv2.putText(frame, line, (text_x, y_pos),
                               cv2.FONT_HERSHEY_DUPLEX, 0.7, (0, 0, 255), 2)
                else:
                    y_pos = y + 20 + i * line_height
                    cv2.putText(frame, line, (text_x, y_pos),
                               cv2.FONT_HERSHEY_DUPLEX, 0.7, (0, 0, 255), 2)
            
        return frame
    
    def save_result(self, analyzed_frame, original_path):
        """
        Сохранение только обработанного изображения
        """
        try:
            filename = os.path.basename(original_path)
            name_without_ext = os.path.splitext(filename)[0]
            result_filename = f"{name_without_ext}_analyzed.jpg"
            result_path = os.path.join(self.output_folder, result_filename)
            
            cv2.imwrite(result_path, analyzed_frame)
            
            print(f"Сохранено: {result_filename}")
            return True
            
        except Exception as e:
            print(f"Ошибка при сохранении: {e}")
            return False
    
    def process_single_image(self, image_path):
        """
        Обработка одного изображения
        """
        print(f"Обработка: {os.path.basename(image_path)}")
        
        original_frame, analysis = self.analyze_image(image_path)
        
        if original_frame is None:
            self.failed_count += 1
            return False
        
        if analysis:
            analyzed_frame = self.draw_results(original_frame.copy(), analysis)
            self.save_result(analyzed_frame, image_path)
        else:
            height, width = original_frame.shape[:2]
            cv2.putText(original_frame, "No face detected", 
                       (width//4, height//2), cv2.FONT_HERSHEY_SIMPLEX, 
                       1, (0, 0, 255), 2)
            self.save_result(original_frame, image_path)
            
        self.processed_count += 1
        return True
    
    def run(self):
        """
        Основной цикл обработки изображений
        """
        
        image_files = self.get_image_files()
        
        print(f"Найдено изображений: {len(image_files)}")
        
        for i, image_path in enumerate(image_files, 1):
            print(f"[{i}/{len(image_files)}] ", end="")
            self.process_single_image(image_path)

        print(f"Успешно обработано: {self.processed_count}")
        print(f"Не удалось обработать: {self.failed_count}")


class FaceAnalysisApp:
    def __init__(self, input_folder=None, output_folder=None):
        """
        Инициализация приложения
        """
        self.analyzer = None
        self.input_folder = input_folder or "images"
        self.output_folder = output_folder or "results"
        
    def run(self):
        """
        Запуск приложения
        """
        try:
            if not os.path.exists(self.input_folder):
                print(f"Папка {self.input_folder} не существует!")
                print(f"Создайте папку '{self.input_folder}' и поместите туда изображения для анализа.")
                return
            
            self.analyzer = ImageFaceAnalyzer(
                input_folder=self.input_folder, 
                output_folder=self.output_folder
            )
            self.analyzer.run()
            
        except Exception as e:
            print(f"Ошибка: {e}")
        finally:
            print("\nРабота программы завершена")


if __name__ == "__main__":
    app = FaceAnalysisApp()
    app.run()